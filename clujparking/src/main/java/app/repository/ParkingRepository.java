package app.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import app.model.Parking;
public interface ParkingRepository extends CrudRepository<Parking,Long> {

	List<Parking> findAll();
	Parking findByParkingName(String name);
	
}
