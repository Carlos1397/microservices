package nttdata.com.bikeservice.model.controller;


import nttdata.com.bikeservice.model.entity.Bike;
import nttdata.com.bikeservice.model.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {
  @Autowired
  BikeService bikeService;

  @GetMapping()
  public ResponseEntity<List<Bike>> getAll() {
    List<Bike> bikeList = bikeService.getAll();
    if (bikeList.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(bikeList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
    Bike bike = bikeService.getCarById(id);
    if (bike == null)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(bike);
  }

  @PostMapping()
  public ResponseEntity<Bike> save(@RequestBody Bike bike) {
    Bike carNew = bikeService.save(bike);
    if (carNew == null)
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(carNew);
  }

  @GetMapping("/byUser/{userId}")
  public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId){
    List<Bike> bikeList = bikeService.byUserId(userId);
    return ResponseEntity.ok(bikeList);
  }

}
