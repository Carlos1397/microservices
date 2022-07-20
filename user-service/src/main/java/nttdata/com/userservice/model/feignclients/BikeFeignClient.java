package nttdata.com.userservice.model.feignclients;

import nttdata.com.userservice.model.capa.Bike;
import nttdata.com.userservice.model.capa.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "bike-service", url="http://localhost:8004", path = "/bikes")
public interface BikeFeignClient {

  @PostMapping()
  Bike Save(@RequestBody Bike bike);


  @GetMapping("/byUser/{userId}")
  List<Bike> getBikes(@PathVariable("userId") int userId);
}
