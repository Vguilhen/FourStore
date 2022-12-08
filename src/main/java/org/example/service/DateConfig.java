package org.example.service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateConfig {

    LocalDateTime localDateTime = LocalDateTime.now();

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public String formatDateTime = localDateTime.format(dateFormatter);

    public String formatDateTime(LocalDateTime localDateTime) {
        return formatDateTime;
    }

}
