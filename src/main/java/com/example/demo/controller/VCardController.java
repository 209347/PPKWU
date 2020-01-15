package com.example.demo.controller;

import com.example.demo.model.UserModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class VCardController {

    @GetMapping("/") public String main() {
        return "index";
    }

    @RequestMapping(value = "/api/getUsersData/{phrase}", method = RequestMethod.GET) @ResponseBody public ArrayList<UserModel> getUsersData(@PathVariable String phrase) throws IOException {
        ArrayList<UserModel> users = new ArrayList<>();

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + phrase).get();
        Elements all = doc.select("div.user-info");
        Elements names =  all.select("h3");
        Elements titles =  all.select("h4");

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
