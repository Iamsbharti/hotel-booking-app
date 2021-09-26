package com.hotel.management.repository;

import com.hotel.management.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,Integer> {

}
