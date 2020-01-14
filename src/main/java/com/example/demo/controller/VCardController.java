package com.example.demo.controller;

import com.example.demo.model.UserModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class VCardController {

    @GetMapping("/api/getUsersData/{phrase}") @ResponseStatus(HttpStatus.OK) public ArrayList<UserModel> getUsersData(@PathVariable String phrase) throws IOException {
        ArrayList<UserModel> users = new ArrayList<>();

        Document doc = Jsoup.connect(
                "https://adm.edu.p.lodz.pl/user/users.php?search=" + phrase).get();
        Elements all = doc.select("div.user-info");
        Elements names =  all.select("h3");
        Elements titles =  all.select("h4");
        Elements places = all.select("span.item-content");

        for(int i = 0; i < all.size(); i++){
            UserModel u = new UserModel();
            u.setName(names.get(i).text().split(" ")[0]);
            u.setSurname(names.get(i).text().split(" ")[1]);
            u.setTitle(titles.get(i).text());
            users.add(u);
        }
        return users;
    }

}
