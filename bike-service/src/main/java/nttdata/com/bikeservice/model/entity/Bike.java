package nttdata.com.bikeservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bikes")
public class Bike {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String brand;

  private String model;

  private int userId;

}
