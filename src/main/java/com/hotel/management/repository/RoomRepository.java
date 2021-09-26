package com.hotel.management.repository;


import com.hotel.management.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room,Integer> {
    @Query("{roomId:'?0'}")
    Optional<Room> findRoomById(String roomId);

    @Query("{roomId:'?0'}")
    Optional<Room> deleteRoomById(String roomId);
}
