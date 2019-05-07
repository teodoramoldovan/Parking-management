package app.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.model.Car;
import app.model.CarModel;
import app.model.Parking;
import app.model.User;
import app.repository.CarRepository;
import app.repository.ParkingRepository;
import app.repository.UserRepository;
import app.rmi.Client;
import app.service.UserService;

@Controller
@ControllerAdvice
public class CarController {
	
	 @Autowired
	    private ParkingRepository parkingData;
	 @Autowired
	 	private CarRepository  carData;
	 @Autowired	
	 private UserService userService;
	 
	
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/user/myCar")
	    public String listParking(Model model) {
	        model.addAttribute("list", parkingData.findAll());
	        return "user/myCar";
	    }
	   @RequestMapping(method = RequestMethod.POST , value = "/user/myCar")  
	    public String editCar(@ModelAttribute(value = "newCar") CarModel newCar, BindingResult result, Model model) throws MalformedURLException, RemoteException, NotBoundException {
	        if (result.hasErrors())
	            return "redirect:/user/myCar";
	        
	        Car car=convertToCar(newCar);
	        Calendar endTime=Calendar.getInstance();
	        endTime.add(endTime.MINUTE, car.getCarTime());
	        car.setEndTime(endTime);
	        Parking parking= car.getCarParking(); 
	        parking.setFreePlaces(parking.getFreePlaces()-1);
	        parking.getListOfCars().add(car);
	        carData.save(car);
	        
	        Client.toDisplay(car.getCarUser().getEmail()+ " parked the car with " + car.getCarName() + " license number ");
	        return "redirect:/user/myCar";
	    }
	   
	   
	   public Car convertToCar(CarModel car) {
		   Car carToReturn = new Car();
		   carToReturn.setCarName(car.getCarName());
		   carToReturn.setCarTime(car.getCarTime());
		   
		   Parking carParking=parkingData.findByParkingName(car.getParkingName());
		   carToReturn.setCarParking(carParking);
		   
		   
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   User user=userService.findUserByEmail(auth.getName());
		   carToReturn.setCarUser(user);
		  
		   return carToReturn;
	   }
	   
	   @RequestMapping(method = RequestMethod.GET, value = "/admin/listCars")
	    public String list(Model model) {
	        model.addAttribute("list", carData.findAll());
	        return "admin/listCars";
	    }
	     
	

}
