package com.hotel.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsResponse {
    private String roomId;
    private List bookingDates;
    private int price;
    private int roomNumber;
}
