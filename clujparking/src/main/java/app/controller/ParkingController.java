package app.controller;
 
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import app.model.Parking;

import app.repository.ParkingRepository;
import app.rmi.Client;
import lombok.Getter;
 
@Controller
@ControllerAdvice

public class ParkingController {
     
    @Autowired
    private ParkingRepository parkingData;
    
    
     
    @RequestMapping(method = RequestMethod.POST , value = "/admin/addNewParking")  
    public String add(@ModelAttribute(value = "newParking") Parking newParking, BindingResult result, Model model) throws MalformedURLException, RemoteException, NotBoundException {
        if (result.hasErrors())
            return "redirect:/admin/addNewParking";
        newParking.setFreePlaces(newParking.getNoOfPlaces());
        parkingData.save(newParking);
        Client.toDisplay("A new parking was added: "+newParking.getParkingName());
        return "redirect:/admin/addNewParking";
    }
     
    @RequestMapping(method = RequestMethod.GET, value = "/admin/addNewParking")
    public String list(Model model) {
        model.addAttribute("list", parkingData.findAll());
        return "admin/addNewParking";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/admin/updateParking")  
    public String update(@ModelAttribute(value = "updatedParking") Parking updatedParking, BindingResult result, Model model) throws MalformedURLException, RemoteException, NotBoundException {
        if (result.hasErrors())
        	if(!parkingData.existsById(updatedParking.getId()))
        		return "redirect:/admin/updateParking";
        parkingData.save(updatedParking);
        Client.toDisplay("Parking: "+updatedParking.getParkingName()+" was updated");
        return "redirect:/admin/updateParking";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/admin/updateParking")
    public String listParking(Model model) {
        model.addAttribute("list", parkingData.findAll());
        return "admin/updateParking";
    }
    @RequestMapping(method = RequestMethod.POST,value="/admin/deleteParking")
    public String delete(@ModelAttribute(value="toDelete") Parking toDelete,BindingResult result, Model model) throws MalformedURLException, RemoteException, NotBoundException {
    	if(result.hasErrors())
    		return "redirect:/admin/deleteParking";
    	Client.toDisplay("Parking: "+toDelete.getParkingName()+" was deleted");
    	parkingData.delete(toDelete);
    	return "redirect:/admin/deleteParking";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/admin/deleteParking")
    public String listParking2(Model model) {
        model.addAttribute("list", parkingData.findAll());
        return "admin/deleteParking";
    }
 

    
}

