package com.example.demo.controller;

import com.example.demo.ICal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class CalendarController {

    @GetMapping("/api/getCurrentMonth") @ResponseStatus(HttpStatus.OK) public ResponseEntity<Resource> getCurrentMonth() throws IOException {
        LocalDate localDate = LocalDate.now();
        String year = String.valueOf(localDate.getYear());
        String month = String.valueOf(localDate.getMonthValue());
        Document document = Jsoup.connect("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month + "&lang=1").get();
        Elements eventElements = document.select("a.active");
        Elements eventNameElements = document.select("div.InnerBox");

        ArrayList<String> eventDates = new ArrayList<>();
        for (Element e : eventElements) {
            if (e.text().length() == 1) {
                eventDates.add("0" + e.text());
            } else {
                eventDates.add(e.text());
            }
        }

        ArrayList<String> eventNames = new ArrayList<>();
        for (Element e : eventNameElements) {
            eventNames.add(e.text());
        }

        ICal.write(eventDates, month, eventNames);
        File file = new File("month" + month + ".ics");
        Resource fileSystemResource = new FileSystemResource(file);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/calendar")).body(fileSystemResource);

    }
//
//    @GetMapping("/api/getCalendarMonth/{month}") @ResponseStatus(HttpStatus.OK) public ResponseEntity<String> getCurrentMonth(@PathVariable String month) {
//        return new ResponseEntity<String>;
//    }

}
