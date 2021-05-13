package com.company.labaforspring.controller;

import com.company.labaforspring.model.Car;
import com.company.labaforspring.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService service;
    @Autowired
    public void setService(CarService cs){
        this.service = cs;
    }
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("cars",service.getAllCars());
        return "cars/index";
    }
    @GetMapping("/details/{id}")
    public String details(@RequestPart("id") int id, Model model){
        Car c = service.getCarById(id);
        model.addAttribute(c);
        return "cars/details";
    }
    @GetMapping("/create")
    public String create(){
        return "cars/create";
    }
    @PostMapping("/create")
    public String create(@RequestParam("name") String name, @RequestParam("description") String description){
        if (name!=null && description!= null){
            Car c = new Car(name, description);
        }
        else {
            throw new NullPointerException("please check input values");
        }
        return "redirect: cars/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@RequestPart("id") int id, Model model){
        Car c = service.getCarById(id);
        if (c!=null){
            model.addAttribute("car",c);
        }
        else {
            throw new NullPointerException("please check input values");
        }
        return "cars/edit";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("date") String date) throws ParseException {
        try{
            if (name!=null && description!=null && date!=null){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date tmp = format.parse(date);
                service.updatingCar(new Car(name, description,tmp));
            }
            else {
                throw new NullPointerException("please check input values");
            }
        }
        catch (ParseException ex){
            throw new ParseException("please enter correct date",0);
        }
        return "redirect: cars/";
    }
    @GetMapping("/delete/{id}")
    public String removing(@RequestPart("id") int id){
        service.removingCar(id);
        return "rrdirect: cars/";
    }
    @GetMapping("/searching")
    public String searching(@RequestParam("name") String name){
        if (name!=null){
            List<Car> cars = service.searchingCarByName(name);
            return "redirect: cars/";
        }
        else {
            throw new NullPointerException("please check input values");
        }
    }
}
