package com.hotel.management.controller;


import com.hotel.management.common.ApiResponse;
import com.hotel.management.model.Booking;
import com.hotel.management.model.BookingDetailsResponse;
import com.hotel.management.model.Room;
import com.hotel.management.model.User;
import com.hotel.management.service.HotelManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Log4j2
@RestController
@RequestMapping("")
public class HotelManagementController {
    @Autowired
    HotelManagementService hotelManagementService;
    @GetMapping()
    public String homeResponse(){
        return "Welcome to HotelManagement API";
    }

    @GetMapping("/rooms/available")
    public ResponseEntity getAllAvailableRooms(@RequestParam(value="dates") String dates){
        List<Room> allAvailableRooms = hotelManagementService.getAllAvailableRoomsService(dates);
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "Rooms Available on "+dates,allAvailableRooms);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/rooms")
    public ResponseEntity saveRoom(@RequestBody Room room,@RequestParam(value ="userId") String userId){
        // verify if userId is available and is admin
        ApiResponse response = null;
        Map userValidityMap = hotelManagementService.isUserValid(userId);
        if(userValidityMap.get("isUserAdmin").equals(true)){
            Room newRoom = hotelManagementService.saveRoomService(room);
            response = new ApiResponse(HttpStatus.OK.toString(), "Room Saved",newRoom);
        }else{
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.toString(), "User Is Not Admin",null);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }
    @PutMapping("/rooms")
    public ResponseEntity updateRoom(@RequestBody Room room,@RequestParam(value ="userId") String userId){
        // verify if userId is available and is admin
        ApiResponse response = null;
        Map userValidityMap = hotelManagementService.isUserValid(userId);
        if(userValidityMap.get("isUserAdmin").equals(true)){
            Room updatedRoom = hotelManagementService.updateRoom(room);
            response = new ApiResponse(HttpStatus.OK.toString(), "Room Updated",updatedRoom);
        }else{
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.toString(), "User Is Not Admin",null);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }
    @DeleteMapping("/rooms")
    public ResponseEntity deleteRoom(@RequestParam (value = "roomId") String roomId,@RequestParam(value ="userId") String userId){
        // verify if userId is available and is admin
        ApiResponse response = null;
        Map userValidityMap = hotelManagementService.isUserValid(userId);
        if(userValidityMap.get("isUserAdmin").equals(true)){
            hotelManagementService.deleteRoom(roomId);
            response = new ApiResponse(HttpStatus.OK.toString(), "Room Deleted",roomId);
        }else{
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.toString(), "User Is Not Admin",null);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user){
        User savedUser = hotelManagementService.saveUser(user);
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(),"User Saved",user);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/book/room")
    public ResponseEntity bookARoom(@RequestParam (value = "roomId") String roomId,
                                    @RequestParam(value ="userId") String userId,
                                    @RequestParam(value="dates") String dates){
        log.info("Booking a room controller");
        Booking newBooking = hotelManagementService.bookARoomService(roomId, userId, dates);
        ApiResponse  response = new ApiResponse(HttpStatus.OK.toString(), "Room Booked",newBooking);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/rooms/booked")
    public ResponseEntity getAllBookedRooms(){
        List <BookingDetailsResponse> bookedRoomDetails = hotelManagementService.getAllBookedRoomsService();
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(),"Booked Rooms",bookedRoomDetails);
        return new ResponseEntity(response,HttpStatus.OK);
    }
    @GetMapping("/revenue")
    public ResponseEntity getTotalRevenue(@RequestParam(value ="userId") String userId){
        int totalRevenue = 0;
        ApiResponse response = null;
        Map userValidityMap = hotelManagementService.isUserValid(userId);

        if(userValidityMap.get("isUserAdmin").equals(true)){
            totalRevenue = hotelManagementService.totalRevenue();
            response = new ApiResponse(HttpStatus.OK.toString(),"Revenue Fetched (INR)",totalRevenue);
        }else{
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.toString(), "User Is Not Admin",null);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
