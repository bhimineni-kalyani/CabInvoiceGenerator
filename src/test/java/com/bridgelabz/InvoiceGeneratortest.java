package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class InvoiceGeneratortest {
    private InvoiceGenerator invoiceGenerator;
    private RideRepository rideRepository;

    @Before
    public void init() {
        invoiceGenerator = new InvoiceGenerator();
        rideRepository = new RideRepository();
    }

    @Test
    public void givenDistanceAndTimeShould_ReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time, InvoiceGenerator.RateType.NORMAL);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturn_MinimumFare() {
        double distance = 0.0;
        int time = 2;
        double fare = invoiceGenerator.calculateFare(distance, time, InvoiceGenerator.RateType.NORMAL);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(3.0, 4),
                new Ride(0.2, 2)
        };
        double fare = invoiceGenerator.calculateFare(rides, InvoiceGenerator.RateType.NORMAL).getTotalFare();
        Assert.assertEquals(39, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(3.0, 4),
                new Ride(0.2, 2)
        };
        InvoiceSummary invoiceSummary =
                invoiceGenerator.calculateFare(rides, InvoiceGenerator.RateType.NORMAL);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 39, 19.5);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenAUserId_ShouldReturnTheInvoiceSummary_ForNormalRides_After_GettingListOfRidesFromRideRepositor() {
        String userId = "code1";
        Ride[] rides = {
                new Ride(2.5, 3),
                new Ride(0.5, 2),
                new Ride(8, 5)
        };
        rideRepository.add(userId, rides);
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(
                userId, rideRepository, InvoiceGenerator.RateType.NORMAL);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 120, 40);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenAUserId_ShouldReturnTheInvoiceSummaryFor_PremiumRides_After_GettingListOfRidesFromRideRepositor() {
        String userId = "code2";
        Ride[] rides = {
                new Ride(2.5, 3),
                new Ride(0.5, 2),
                new Ride(8, 5)
        };
        rideRepository.add(userId, rides);
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(
                userId, rideRepository, InvoiceGenerator.RateType.PREMIUM);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 193.5, 64.5);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }
}