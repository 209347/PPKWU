package com.example.demo.controller;

import com.example.demo.model.SearchModel;
import com.example.demo.model.UserModel;
import com.google.gson.Gson;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.Revision;
import ezvcard.property.StructuredName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class VCardController {

    @GetMapping("/")
    public String main(Model model) {
        SearchModel searchModel = new SearchModel();
        searchModel.setPhrase("");
        model.addAttribute("searchModel", searchModel);
        return "index";
    }

    @GetMapping("/getvCards")
    public String getvCards(@ModelAttribute SearchModel searchModel, Model model) throws IOException {
        model.addAttribute("searchInput", searchModel);
        ArrayList<UserModel> users = getUsersData(searchModel.getPhrase());
        model.addAttribute("users", users);
        return "getvCards";
    }

    @RequestMapping(value = "/api/getUsersData/{phrase}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<UserModel> getUsersData(@PathVariable String phrase) throws IOException {
        ArrayList<UserModel> users = new ArrayList<>();

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + phrase).get();
        Elements all = doc.select("div.user-info");
        Elements names =  all.select("h3");
        Elements titles =  all.select("h4");
        Elements workPlaces = all.select("span.item-content");

        for(int i = 0; i < all.size(); i++){
            UserModel u = new UserModel();
            u.setName(names.get(i).text().split(" ")[0]);
            u.setSurname(names.get(i).text().split(" ")[1]);
            u.setTitle(titles.get(i).text());
            u.setWorkPlace(workPlaces.get(i).text());
            users.add(u);
        }
        return users;
    }

    @RequestMapping(value = "/api/generatevCard/{user}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> generatevCard(@PathVariable String user) throws IOException {
        Gson gson = new Gson();
        UserModel userModel =  gson.fromJson(user, UserModel.class);
        VCard vcard = getVCard(userModel);

        File vcardFile = new File("vcard.vcf");
        Ezvcard.write(vcard).version(VCardVersion.V4_0).go(vcardFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vcard.vcf");
        Resource fileSystemResource = new FileSystemResource("vcard.vcf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileSystemResource);
    }

    public VCard getVCard(UserModel user) throws IOException {
        VCard vcard = new VCard();
        StructuredName structuredName = new StructuredName();
        structuredName.setFamily(user.getSurname());
        structuredName.setGiven(user.getName());
        if (user.getTitle() != null) {
            vcard.addTitle(user.getTitle());
        }
        if (user.getWorkPlace() != null) {
            vcard.setOrganization(user.getWorkPlace());
        }
        vcard.setFormattedName(user.getName() + " " + user.getSurname());
        vcard.setRevision(Revision.now());

        return vcard;
    }

}
