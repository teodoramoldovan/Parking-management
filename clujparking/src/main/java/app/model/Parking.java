package app.model;

import lombok.Data;
import lombok.Getter;
import lombok.AccessLevel;

import org.hibernate.validator.constraints.URL;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@Getter(AccessLevel.PUBLIC)
@Entity
public class Parking {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String parkingName;
	     private String parkingAddress;
	     private int noOfPlaces;
	     @OneToMany(mappedBy="carParking",cascade = CascadeType.ALL)
	     private List<Car> listOfCars;
	     private int freePlaces;
	     
	    public Parking() {}

	    public Parking(String name, String address,int places) {
	        this.parkingName=name;
	        this.parkingAddress=address;
	        this.noOfPlaces=places;
	    }
	    
	    
}
