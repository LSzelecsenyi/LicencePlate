package com.lszelecsenyi.licenceplates.controller;

import com.lszelecsenyi.licenceplates.model.LicencePlate;
import com.lszelecsenyi.licenceplates.model.Response;
import com.lszelecsenyi.licenceplates.repo.LicencePlateRepo;
import com.lszelecsenyi.licenceplates.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/licenceplates")
public class LPMainController {

    @Autowired
    LicencePlateRepo licencePlateRepo;

    @Autowired
    PlateService plateService;

//    @GetMapping("/search")
//    public String search(@RequestParam(value = "q", required = false) String q, Model model) {
//        if (q == null) {
//            model.addAttribute("list", licencePlateRepo.findAll());
//            return "index";
//        } else
//        model.addAttribute("list", licencePlateRepo.findAllByPlateContaining(q));
//        return "index";
//    }

//    @GetMapping("/search")
//    public String search(@RequestParam(value = "q", required = false) String q, Model model) {
//        if(q == null) {
//            model.addAttribute("list", licencePlateRepo.findAll());
//            return "index";
//        } else if (q != null && !q.equals("diplomat") && !q.equals("police")) {
//            model.addAttribute("list", licencePlateRepo.findAllByPlateContaining(q));
//            return "index";
//        } else if (q.equals("diplomat")) {
//            model.addAttribute("list", licencePlateRepo.findAllByPlateStartingWith("DT"));
//            return "index";
//        } else if(q.equals("police")) {
//            model.addAttribute("list", licencePlateRepo.findAllByPlateStartingWith("RB"));
//            return "index";
//        } else
//            model.addAttribute("list", licencePlateRepo.findAll());
//            return "index";
//    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", licencePlateRepo.findAll());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String q, Model model) {
        String warnMessage = "";
        //additional feature for more FUN
        if (q.equals("")) {
            warnMessage = "Please enter licence plate number!";
            model.addAttribute("warnMessage", warnMessage);
            return "search";
        //additional feature for more FUN
        } else if (licencePlateRepo.findAllByPlateContaining(q).size() == 0 && !q.equals("diplomat") && !q.equals("police")) {
            warnMessage = "Database does not contain the searched licence plate";
            model.addAttribute("warnMessage", warnMessage);
        } else if (q != null && !q.equals("diplomat") && !q.equals("police") && q.matches("[0-9a-zA-Z.\\-]*") && q.length() <= 7) {
            model.addAttribute("list", licencePlateRepo.findAllByPlateContaining(q));
            return "search";
        } else if (q.equals("diplomat")) {
            model.addAttribute("list", licencePlateRepo.findAllByPlateStartingWith("DT"));
            return "search";
        } else if(q.equals("police")) {
            model.addAttribute("list", licencePlateRepo.findAllByPlateStartingWith("RB"));
            return "search";
        } else if (!q.matches("[A-Za-z0-9.\\-]*") || q.length() < 7 || q.length() > 7)
            warnMessage = "Sorry, the submitted licence plate is not valid";
            model.addAttribute("warnMessage", warnMessage);
            return "search";
    }

    @GetMapping("/search/{brand}")
    public String byBrand(@PathVariable String brand, Model model) {
        model.addAttribute("list", licencePlateRepo.findAllByCarBrand(brand));
        return "search";
    }

//    @GetMapping("/search")
//    @ResponseBody
//    public Object getByBrand(@RequestParam(value = "brand", required = false) String brand) {
//        if (brand != null) {
//            Iterable listByBrand = licencePlateRepo.findAllByCarBrand(brand);
//            return new Response(listByBrand);
//        } else if (brand == null) {
//            return new Response("something went wrong dumbass");
//        } else return new Response("baaad, bad request");
//    }


}
