package com.hotel.management;


import com.hotel.management.model.Booking;
import com.hotel.management.model.Dates;
import com.hotel.management.model.Room;
import com.hotel.management.model.User;
import com.hotel.management.repository.BookingRepository;
import com.hotel.management.repository.DatesRepository;
import com.hotel.management.repository.RoomRepository;
import com.hotel.management.repository.UserRepository;
import com.hotel.management.service.HotelManagementService;
import com.hotel.management.service.HotelManagementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ServiceTest {
    @InjectMocks
    HotelManagementServiceImpl serviceTest;

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    DatesRepository datesRepository;

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(serviceTest,"bookingRepository",bookingRepository);
        ReflectionTestUtils.setField(serviceTest,"datesRepository",datesRepository);
        ReflectionTestUtils.setField(serviceTest,"roomRepository",roomRepository);
        ReflectionTestUtils.setField(serviceTest,"userRepository",userRepository);
    }

    @Test
    public void test_saveUser(){
        User user = new User("id","fname","lname",false);
        when(userRepository.save(any())).thenReturn(user);
        assertThat(serviceTest.saveUser(user)).isNotNull();
    }

    @Test(expected = Exception.class)
    public void totalRevenue(){
        List<Booking> allBooking = new ArrayList<>();
        allBooking.add(new Booking());
        when(bookingRepository.findAll()).thenReturn(allBooking);
        assertThat(serviceTest.totalRevenue()).isNotNull();
    }

    @Test
    public void test_saveRoomService(){
        Room room = new Room();
        when(roomRepository.save(room)).thenReturn(room);
        assertThat(serviceTest.saveRoomService(room)).isNotNull();
    }

    @Test
    public void test_isUserValid(){
        when(userRepository.findByTheUserId(anyString())).thenReturn(java.util.Optional.of(new User("id","fname","lname",true)));
        Map resultsMap = serviceTest.isUserValid("id");
        assertThat(resultsMap.get("isValidUser")).isEqualTo(true);
    }

    @Test(expected = Exception.class)
    public void test_getAllAvailableRooms(){
        List<Dates> allBookingDates = new ArrayList<>();
        allBookingDates.add(new Dates());
        List<Room> allRooms = new ArrayList<>();
        allRooms.add(new Room());

        when(datesRepository.findAll()).thenReturn(allBookingDates);
        when(roomRepository.findAll()).thenReturn(allRooms);

        assertThat(serviceTest.getAllAvailableRoomsService("dates")).isNotNull();
    }

}
