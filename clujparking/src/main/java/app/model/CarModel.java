package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.AccessLevel;


@Data
@Getter(AccessLevel.PUBLIC)
public class CarModel {
	
		  	private String carName;
		  	private String parkingName;
		    private int carTime;	

}
