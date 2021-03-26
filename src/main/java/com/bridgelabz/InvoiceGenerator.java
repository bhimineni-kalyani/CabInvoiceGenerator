package com.bridgelabz;

public class InvoiceGenerator {
    enum RateType {
        NORMAL(10.0, 1.0, 5.0),
        PREMIUM(15.0, 2.0, 20.0);

            private final double MINIMUM_COST_PER_KILOMETER;
            private final double COST_PER_TIME;
            private final double MINIMUM_FARE;

            RateType(double minCostPerKm, double costPerTime, double minFare) {
                this.MINIMUM_COST_PER_KILOMETER = minCostPerKm;
                this.COST_PER_TIME = costPerTime;
                this.MINIMUM_FARE = minFare;
            }
    }

    public double calculateFare(double distance, int time, RateType rateType) {
        double fare = distance * rateType.MINIMUM_COST_PER_KILOMETER + time * rateType.COST_PER_TIME;
        return (fare < rateType.MINIMUM_FARE) ? rateType.MINIMUM_FARE : fare;
    }

    public InvoiceSummary calculateFare(Ride[] rides, RateType rateType) {
        double total_fare = 0;
        for(Ride ride : rides)
            total_fare += calculateFare(ride.getDistance(), ride.getTime(), rateType);
            return new InvoiceSummary(rides.length, total_fare, total_fare/ rides.length);
        }

    public InvoiceSummary calculateFare(String userId, RideRepository rideRepository, RateType rateType) {
        if (!rideRepository.contains(userId))
            return null;
        return calculateFare(rideRepository.toArray(userId), rateType);
    }
}