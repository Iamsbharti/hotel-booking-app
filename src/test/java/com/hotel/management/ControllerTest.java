package com.hotel.management;

import com.hotel.management.controller.HotelManagementController;
import com.hotel.management.model.Booking;
import com.hotel.management.model.BookingDetailsResponse;
import com.hotel.management.model.Room;
import com.hotel.management.model.User;
import com.hotel.management.service.HotelManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ControllerTest {
    @InjectMocks
    HotelManagementController controllerTest;
    @MockBean
    HotelManagementService hotelManagementService;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(controllerTest,"hotelManagementService",hotelManagementService);
    }
    @Test()
    public void test_home(){
        assertThat(controllerTest.homeResponse()).isNotNull();
    }

    @Test
    public void test_getAllAvailableRooms(){
        List<Room> mockResponse = new ArrayList();
        mockResponse.add(new Room("12",200,400));
        when(hotelManagementService.getAllAvailableRoomsService(anyString())).thenReturn(mockResponse);

        ResponseEntity actualResponse = controllerTest.getAllAvailableRooms("23/03/2021");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void test_saveRoom(){
        Map resultsMap = new HashMap<>();
        resultsMap.put("isValidUser",true);
        resultsMap.put("isUserAdmin",true);
        Room room = new Room("12",200,400);
        when(hotelManagementService.isUserValid(anyString())).thenReturn(resultsMap);
        when(hotelManagementService.saveRoomService(any())).thenReturn(room);

        ResponseEntity actualResponse = controllerTest.saveRoom(room,"userId");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void test_updateRoom(){
        Map resultsMap = new HashMap<>();
        resultsMap.put("isValidUser",true);
        resultsMap.put("isUserAdmin",true);
        Room room = new Room("12",200,400);
        when(hotelManagementService.isUserValid(anyString())).thenReturn(resultsMap);
        when(hotelManagementService.updateRoom(any())).thenReturn(room);

        ResponseEntity actualResponse = controllerTest.saveRoom(room,"userId");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void test_deleteRoom(){
        Map resultsMap = new HashMap<>();
        resultsMap.put("isValidUser",true);
        resultsMap.put("isUserAdmin",true);
        Room room = new Room("12",200,400);
        when(hotelManagementService.isUserValid(anyString())).thenReturn(resultsMap);

        ResponseEntity actualResponse = controllerTest.deleteRoom("roomId","userId");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_saveUser(){
        User user = new User("id","fname","lname",false);
        when(hotelManagementService.saveUser(any())).thenReturn(user);
        ResponseEntity actualResponse = controllerTest.deleteRoom("roomId","userId");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_bookRoom(){
        when(hotelManagementService.bookARoomService(anyString(),anyString(),anyString())).thenReturn(new Booking());
        ResponseEntity actualResponse = controllerTest.bookARoom("roomId","userId","dates");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_getAllBookedRooms(){
        List <BookingDetailsResponse> bookedRoomDetails = new ArrayList<>();
        bookedRoomDetails.add(new BookingDetailsResponse());

        when(hotelManagementService.getAllBookedRoomsService()).thenReturn(bookedRoomDetails);
        ResponseEntity actualResponse = controllerTest.getAllBookedRooms();
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_getTotalRevenue(){
        Map resultsMap = new HashMap<>();
        resultsMap.put("isValidUser",true);
        resultsMap.put("isUserAdmin",true);
        Room room = new Room("12",200,400);
        when(hotelManagementService.isUserValid(anyString())).thenReturn(resultsMap);

        when(hotelManagementService.totalRevenue()).thenReturn(1243);
        ResponseEntity actualResponse = controllerTest.getTotalRevenue("userid");
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
