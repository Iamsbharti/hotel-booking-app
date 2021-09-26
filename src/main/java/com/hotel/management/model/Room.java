package com.hotel.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private String roomId;
    private int roomNumber;
    private int price;
}
