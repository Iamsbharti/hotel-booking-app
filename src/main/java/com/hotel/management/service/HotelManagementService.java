package com.hotel.management.service;

import com.hotel.management.common.ApiResponse;
import com.hotel.management.model.Booking;
import com.hotel.management.model.BookingDetailsResponse;
import com.hotel.management.model.Room;
import com.hotel.management.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HotelManagementService {
    // user services
    public abstract List<Room> getAllAvailableRoomsService(String date);
    public abstract Booking bookARoomService(String roomId,String userId,String dates);
    public abstract User saveUser(User user);

    // admin services
    public abstract List<BookingDetailsResponse> getAllBookedRoomsService();
    public abstract Integer totalRevenue();
    public abstract Room saveRoomService(Room room);
    public abstract void deleteRoom(String roomId);
    public abstract Room updateRoom(Room room);
    public abstract Map<String, Boolean> isUserValid(String userId);
}
