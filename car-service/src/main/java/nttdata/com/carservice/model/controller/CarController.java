package nttdata.com.carservice.model.controller;

import nttdata.com.carservice.model.entity.Car;
import nttdata.com.carservice.model.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {
  @Autowired
  CarService carService;

  @GetMapping()
  public ResponseEntity<List<Car>> getAll() {
    List<Car> cars = carService.getAll();
    if (cars.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(cars);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Car> getById(@PathVariable("id") int id) {
    Car car = carService.getCarById(id);
    if (car == null)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(car);
  }

  @PostMapping()
  public ResponseEntity<Car> save(@RequestBody Car car) {
    Car carNew = carService.save(car);
    if (carNew == null)
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(carNew);
  }

  @GetMapping("/byUser/{userId}")
  public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
    List<Car> carList = carService.byUserId(userId);
    return ResponseEntity.ok(carList);
  }

}
