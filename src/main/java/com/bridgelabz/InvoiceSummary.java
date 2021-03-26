package com.bridgelabz;

public class InvoiceSummary {
    private int totalNoOfRides;
    private double totalFare;
    private double averageFare;

    public InvoiceSummary(int totalNoOfRides, double totalFare, double averageFare) {
        this.totalNoOfRides = totalNoOfRides;
        this.totalFare = totalFare;
        this.averageFare = averageFare;
    }

    public double getTotalFare() {
        return this.totalFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return totalNoOfRides == that.totalNoOfRides
                && Double.compare(that.totalFare, totalFare) == 0
                && Double.compare(that.averageFare, averageFare) == 0;
    }
}