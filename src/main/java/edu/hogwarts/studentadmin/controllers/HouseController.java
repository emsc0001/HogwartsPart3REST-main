package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/houses")
@RestController
public class HouseController {
    private final HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public List<House> getAllHouses() {
        List<House> allHouses = houseRepository.findAll();

        return allHouses;
    }

    @GetMapping("/{name}")
    public ResponseEntity<House> getHouse(@PathVariable String name) {
        Optional<House> house = houseRepository.findByNameContainingIgnoreCase(name);

        return ResponseEntity.of(house);
    }

    @PostMapping
    public House createHouse(@RequestBody House house) {
        return houseRepository.save(house);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable int id, @RequestBody House house) {
        Optional<House> objectToUpdate = houseRepository.findById(id);

        if(objectToUpdate.isPresent()) {
            House objectGettingUpdated = objectToUpdate.get();
            objectGettingUpdated.copyFrom(house);

            House updatedHouse = houseRepository.save(objectGettingUpdated);
            return ResponseEntity.ok().body(updatedHouse);
        } else {
            House newHouse = new House(house);

            House savedHouse = houseRepository.save(newHouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHouse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable int id) {
        Optional<House> houseToDelete = houseRepository.findById(id);
        houseRepository.deleteById(id);

        return ResponseEntity.of(houseToDelete);
    }
}
