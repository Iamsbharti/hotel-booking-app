package com.hotel.management.service;

import com.hotel.management.model.*;
import com.hotel.management.repository.BookingRepository;
import com.hotel.management.repository.DatesRepository;
import com.hotel.management.repository.RoomRepository;
import com.hotel.management.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class HotelManagementServiceImpl implements HotelManagementService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    DatesRepository datesRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    public List<Room> getAllAvailableRoomsService(String date) {
        // get the ranges if present
        List datesList = new ArrayList();
        if(date.contains(",")){
            for(String dt: date.split(",")){
                datesList.add(date);
            }
        }else{
            datesList.add(date);
        }

        // get all bookings
        List<Dates> allBookingDates = datesRepository.findAll();
        // get all rooms
        List<Room> allRooms = roomRepository.findAll();

        // filter room ids which are not present
        // in booking list for a given date
        log.info("allBookingDates::"+allBookingDates);
        List<String> notAvailableRoomsIds = new ArrayList();
        List<Room> allAvailableRooms = new ArrayList();

        for(Dates bookedDates:allBookingDates){
            log.info("validation condition::"+bookedDates.getDates().contains(date)+"::"+bookedDates.getDates());
            if(bookedDates.getDates().contains(date)){
                notAvailableRoomsIds.add(bookedDates.getRoomId());
            }
        }
        log.info("notAvailableRoomsIds list::"+notAvailableRoomsIds);

        // filter all available rooms
        for(Room room: allRooms){
            if(!notAvailableRoomsIds.contains(room.getRoomId())){
                allAvailableRooms.add(room);
            }
        }
        log.info("room available::",allAvailableRooms);
        return allAvailableRooms;
    }

    @Override
    public Booking bookARoomService(String roomId,String userId,String dates) {
        // get the room details from roomId
        Optional<Room> bookedRoom = roomRepository.findRoomById(roomId);
        Optional<User> user = userRepository.findByTheUserId(userId);
        log.info("Booked room:",bookedRoom);
        log.info("user booking:",user);

        // new booking dates schema
        Dates bookingDatesObj  = new Dates();
        List datesList = new ArrayList();
        for(String date: dates.split(",")){
            datesList.add(date);
        }
        bookingDatesObj.setDates(datesList);
        bookingDatesObj.setRoomId(bookedRoom.get().getRoomId());
        log.info("bookingDatesObj:"+bookingDatesObj.getDates());
        // persist dates schema
        Dates bookingDates = datesRepository.save(bookingDatesObj);

        // update booking details
        Booking bookingInfo = new Booking();
        bookingInfo.setUser(user.get());
        bookingInfo.setRoom(bookedRoom.get());
        bookingInfo.setDates(bookingDates);
        log.info("Booking details::"+bookingInfo);

        // save booking details
        return bookingRepository.save(bookingInfo);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<BookingDetailsResponse> getAllBookedRoomsService() {
        List<Booking> allBookings = bookingRepository.findAll();
        BookingDetailsResponse details = new BookingDetailsResponse();
        List<BookingDetailsResponse> bookingDetails = new ArrayList<>();

        for(Booking booking: allBookings){
            details.setRoomId(booking.getRoom().getRoomId());
            details.setPrice(booking.getRoom().getPrice());
            details.setBookingDates(booking.getDates().getDates());
            details.setRoomNumber(booking.getRoom().getRoomNumber());
            bookingDetails.add(details);
        }
        log.info("Booking details::",bookingDetails);
        return bookingDetails;
    }

    @Override
    public Integer totalRevenue() {
        List<Booking> allBooking = bookingRepository.findAll();
        int totalRevenue = 0;
        for(Booking bookedRooms:allBooking){
            totalRevenue+=bookedRooms.getRoom().getPrice();
        }
        log.info("Total revenue::"+totalRevenue);
        return totalRevenue;
    }

    @Override
    public Room saveRoomService(Room room) {
        /**
         *  **/
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String roomId) {
         roomRepository.deleteRoomById(roomId);
    }

    @Override
    public Room updateRoom(Room room) {
        Optional<Room> toUpdateRoom = roomRepository.findRoomById(room.getRoomId());
        log.info("Update room:"+toUpdateRoom);
        Room updatedRoom = null;
        if(toUpdateRoom.isPresent()){
            updatedRoom = toUpdateRoom.get();
            updatedRoom.setPrice(room.getPrice());
            updatedRoom.setRoomNumber(room.getRoomNumber());
            roomRepository.save(updatedRoom);
        }
        log.info("Final updated room::"+updatedRoom);
        return updatedRoom;
    }

    @Override
    public Map<String,Boolean> isUserValid(String userId) {
        log.info("isUserAdmin:"+userId);
        Map<String,Boolean> resultsMap = new HashMap();
        // verify if userid is admin
        Optional<User> user = userRepository.findByTheUserId(userId);

        if(user.isPresent() && user.get().isAdmin()){
            resultsMap.put("isValidUser",true);
            resultsMap.put("isUserAdmin",true);
        }else{
            resultsMap.put("isValidUser",false);
            resultsMap.put("isUserAdmin",false);
        }
        return resultsMap;
    }
}
