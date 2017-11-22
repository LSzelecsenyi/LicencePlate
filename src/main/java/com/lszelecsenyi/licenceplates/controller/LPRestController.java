package com.lszelecsenyi.licenceplates.controller;

import com.lszelecsenyi.licenceplates.model.Response;
import com.lszelecsenyi.licenceplates.repo.LicencePlateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LPRestController {

    @Autowired
    LicencePlateRepo licencePlateRepo;

    @GetMapping("/search")
    public Object getByBrand(@RequestParam(value = "brand", required = false) String brand) {
        if (brand != null) {
            Iterable listByBrand = licencePlateRepo.findAllByCarBrand(brand);
            return new Response(listByBrand);
        } else if (brand == null) {
            return new Response("something went wrong dumbass");
        } else return new Response("baaad, bad request");
    }

}
