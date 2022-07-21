package nttdata.com.userservice.model.service;

import nttdata.com.userservice.model.capa.Bike;
import nttdata.com.userservice.model.capa.Car;
import nttdata.com.userservice.model.entity.User;
import nttdata.com.userservice.model.feignclients.BikeFeignClient;
import nttdata.com.userservice.model.feignclients.CarFeignClient;
import nttdata.com.userservice.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  CarFeignClient carFeignClient;
  @Autowired
  BikeFeignClient bikeFeignClient;

  public List<User> getAll(){
    return userRepository.findAll();
  }
  public  User getUserById(int id){
    return userRepository.findById(id).orElse(null);
  }
  public User save(User user){
    return userRepository.save(user);
  }

  public List<Car> getCars(int userId){
    List<Car> cars = restTemplate.getForObject("http://car-service/cars/byUser/"+ userId,List.class);
    return cars;
  }
  public List<Bike> getBikes(int userId){
    List<Bike> bikeList = restTemplate.getForObject("http://bike-service:8004/bikes/byUser/"+ userId,List.class);
    return bikeList;
  }
  public Car saveCar(int userId,Car car){
    car.setUserId(userId);
    return carFeignClient.Save(car);
  }
  public Bike saveBike(int userId,Bike bike){
    bike.setUserId(userId);
    return bikeFeignClient.Save(bike);
  }

  public Map<String, Object> getUserAndVehicles(int userId){
    Map<String, Object> result = new HashMap<>();
    User user = userRepository.findById(userId).orElse(null);
    if(user == null){
      result.put("Message","NOT FOUND USER");
      return result;
    }
    result.put("User",user);
    List<Car> cars = carFeignClient.getCars(userId);
    if(cars.isEmpty())
      result.put("Cars","USER NOT FOUND CARS");
    else
      result.put("Cars",cars);
    List<Bike> bikes = bikeFeignClient.getBikes(userId);
    if(bikes.isEmpty())
      result.put("Bikes", "USER NOT FOUND BIKES");
    else
      result.put("Bikes", bikes);
    return result;
  }

}
