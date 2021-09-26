package com.hotel.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingSuccessResponse {
    private List<String> roomIds;
    private int totalPrice;
    private List<String> bookedDates;
    private String name;
}
