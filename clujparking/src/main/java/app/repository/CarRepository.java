package app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.model.Car;
import app.model.Parking;

public interface CarRepository extends CrudRepository<Car,Long>{

	List<Car> findAll();
}
