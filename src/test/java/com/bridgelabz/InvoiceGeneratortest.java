package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class InvoiceGeneratortest {
    private InvoiceGenerator invoiceGenerator;

    @Before
    public void init() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTimeShould_ReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturn_MinimumFare(){
        double distance = 0.0;
        int time = 2;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare(){
        Ride[] rides = {
                new Ride(3.0, 4),
                new Ride(0.2, 2)
        };
        double fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(39, fare, 0.0);
    }
}