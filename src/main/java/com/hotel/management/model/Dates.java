package com.hotel.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dates {
    @Id
    private String datesId;
    private List<String> dates;
    private String roomId;
}
