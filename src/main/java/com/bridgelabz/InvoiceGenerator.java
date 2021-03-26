package com.bridgelabz;

public class InvoiceGenerator {
    enum RateType {
        NORMAL(3.0, 1.0, 9.0),
        PREMIUM(23.0, 2.0, 18.0);

        private final double minimumcostperkilometer;
        private final double costpertime;
        private final double minimumfare;

        RateType(double minCostPerKm, double costPerTime, double minFare) {
            this.minimumcostperkilometer = minCostPerKm;
            this.costpertime = costPerTime;
            this.minimumfare = minFare;
        }
    }

    public double calculateFare(double distance, int time, RateType rateType) {
        double fare = distance * rateType.minimumcostperkilometer+ time * rateType.costpertime;
        return (fare < rateType.minimumfare) ? rateType.minimumfare : fare;
    }

    public InvoiceSummary calculateFare(Ride[] rides, RateType rateType) {
        double total_fare = 0;
        for(Ride ride : rides)
            total_fare += calculateFare(ride.getDistance(), ride.getTime(), rateType);
        return new InvoiceSummary(rides.length, total_fare, total_fare/ rides.length);
    }

    public InvoiceSummary calculateFare(String userId, RideRepository rideRepository, RateType rateType) {
        if(!rideRepository.contains(userId))
            return null;
        return calculateFare(rideRepository.toArray(userId), rateType);
    }
}