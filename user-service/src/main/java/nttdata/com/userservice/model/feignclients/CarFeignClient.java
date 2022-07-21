package nttdata.com.userservice.model.feignclients;

import nttdata.com.userservice.model.capa.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "car-service", path = "/cars")
public interface CarFeignClient {

  @PostMapping()
  Car Save(@RequestBody Car car);

  @GetMapping("/byUser/{userId}")
  List<Car> getCars(@PathVariable("userId") int userId);
}
