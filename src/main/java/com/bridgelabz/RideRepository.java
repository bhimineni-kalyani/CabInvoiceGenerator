package com.bridgelabz;

import java.util.*;

public class RideRepository {
    private final Map<String,List<Ride>> repository;

    public RideRepository() {
        repository = new HashMap<>();
    }

    public void add(String userid,Ride[] rides) {
        repository.put(userid,new ArrayList<>(Arrays.asList(rides)));
    }

    public boolean contains(String userId) {
        return repository.containsKey(userId);
    }

    public Ride[] toArray(String userId) {
        if(!repository.containsKey(userId))
            return null;
        return repository.get(userId).toArray(new Ride[0]);
    }
}