package com.bridgelabz;

public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_TIME = 1.0;
    private static final double MINIMUM_FARE = 5.0;
    public double calculateFare(double distance, int time) {
        double fare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return (fare < MINIMUM_FARE) ? MINIMUM_FARE : fare;
    }
    public InvoiceSummary calculateFare(Ride[] rides) {
        double total_fare = 0;
        for(Ride ride : rides)
            total_fare += calculateFare(ride.getDistance(), ride.getTime());
        return new InvoiceSummary(rides.length, total_fare, total_fare/ rides.length);
    }

    public InvoiceSummary calculateFare(String userId, RideRepository rideRepository) {
        if(!rideRepository.contains(userId))
            return null;
        return calculateFare(rideRepository.toArray(userId));
    }
}