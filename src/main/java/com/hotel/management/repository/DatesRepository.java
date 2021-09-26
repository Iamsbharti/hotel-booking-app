package com.hotel.management.repository;


import com.hotel.management.model.Dates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatesRepository extends MongoRepository<Dates,Integer> {
}
