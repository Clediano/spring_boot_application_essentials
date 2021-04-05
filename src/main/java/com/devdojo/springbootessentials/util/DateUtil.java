package com.devdojo.springbootessentials.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDate) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDate);
    }
}
