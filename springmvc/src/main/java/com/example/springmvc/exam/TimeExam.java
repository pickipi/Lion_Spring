package com.example.springmvc.exam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class TimeExam {
    public static void main(String[] args) {
//        System.out.println(ZonedDateTime.now(ZonedId.of("America/Belem")););

        LocalDate firstDate = LocalDate.of(2025, 1, 1);
        System.out.println(firstDate.plusDays(100));

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        System.out.println(ZonedDateTime.now(ZoneId.of("America/Belem")));

        Set<String> zonedIds = ZoneId.getAvailableZoneIds();
        for(String zoneId : zonedIds){
            System.out.println(zoneId);
        }
    }
}
