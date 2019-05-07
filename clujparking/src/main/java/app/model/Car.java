package app.model;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Getter(AccessLevel.PUBLIC)
@Entity
public class Car {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  	private String carName;
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id")
	  	private User carUser;
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="parking_id")
	  	private Parking carParking;
	    private int carTime;
	    private Calendar endTime;
	    
	    
	    public String convertEndTime() {
			   
			   SimpleDateFormat df=new SimpleDateFormat("HH:mm");
			   String endTimeString=df.format(endTime.getTime());
			   
			   return endTimeString;
			      
		   }
		    
	    
	    
//		@Override
//		public void run() {
//			try {
//				while(carTime>0)
//				{
//					carTime--;
//					Thread.sleep(60000);
//				}
//			}
//				catch (InterruptedException e) {
//				     System.out.println(carName + "Interrupted");
//			}
//		}
	    
	    
	    
}
