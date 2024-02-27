package kea.exercise.studentadmin.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public List<HouseResponseDTO> getAll() {
        return houseService.findAll();
    }


    @RestController
    @RequestMapping("/houses")
    public static class HouseController {
        private final HouseService houseService;

        public HouseController(HouseService houseService) {
            this.houseService = houseService;
        }

        @GetMapping
        public List<HouseResponseDTO> getAll() {
            return houseService.findAll();
        }


    }
}