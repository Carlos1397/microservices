package nttdata.com.userservice.model.capa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  private String brand;
  private String model;
  private int userId;

}
