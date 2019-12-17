package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ICal {

    private static final String CALENDAR_BEGIN = "BEGIN:VCALENDAR\r\n";
    private static final String VERSION = "VERSION:2.0\r\n";
    private static final String PRODID = "PRODID:-//Ben Fortuna//iCal4j 1.0//EN\r\n";
    private static final String CALSCALE = "CALSCALE:GREGORIAN\r\n";
    private static final String METHOD = "METHOD:PUBLISH\r\n";
    private static final String EVENT_BEGIN = "BEGIN:VEVENT\r\n";
    private static final String START_DATE = "DTSTART:2019";
    private static final String END_DATE = "DTEND:2019";
    private static final String SUMMARY = "SUMMARY:";
    private static final String EVENT_END = "END:VEVENT\r\n";
    private static final String CALENDAR_END = "END:VCALENDAR\r\n";
    private static final String BREAKLINE = "\r\n";
    private static final String FILE_NAME = "month";
    private static final String FILE_TYPE = ".ics";

    public static void write(List<String> eventDates, String month, List<String> eventName) {
        StringBuilder builder = new StringBuilder();
        builder.append(FILE_NAME);
        builder.append(month);
        builder.append(FILE_TYPE);

        try {
            File file = new File(builder.toString());
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(CALENDAR_BEGIN);
            bufferedWriter.write(VERSION);
            bufferedWriter.write(PRODID);
            bufferedWriter.write(CALSCALE);
            bufferedWriter.write(METHOD);

            for (int i = 0; i < eventDates.size(); i++) {
                bufferedWriter.write(EVENT_BEGIN);
                bufferedWriter.write(START_DATE + month + eventDates.get(i) + BREAKLINE);
                bufferedWriter.write(END_DATE + month + eventDates.get(i) + BREAKLINE);
                bufferedWriter.write(SUMMARY + eventName.get(i) + BREAKLINE);
                bufferedWriter.write(EVENT_END);
            }
            bufferedWriter.write(CALENDAR_END);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}