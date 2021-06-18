package com.example.demo.controller;

import com.example.demo.model.Mouse;
import com.example.demo.sevice.MouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mouse")
public class MouseController {
    private MouseService service;
    @Autowired
    public void setService(MouseService service) {
        this.service = service;
    }

    @GetMapping("/index")

    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        var d = service.getAllMouses();
        mav.addObject("mouses", service.getAllMouses());
        mav.setViewName("mouse/index");
        return mav;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("mouse", service.getDetails(id));
        mav.setViewName("mouse/details");
        return mav;
    }
    @GetMapping("/new")
    public ModelAndView adding(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mouse/new");
        mav.addObject("mouse", new Mouse());
        return mav;
    }
    @PostMapping("/new")
    public String addingPost(Mouse m){
        service.addingMouse(m);
        return "redirect:/mouse/index";
    }
    @GetMapping("/edit/{id}")
    private String edit(@PathVariable int id, Model model){
        model.addAttribute(service.getDetails(id));
        return "mouse/edit";
    }
    @PostMapping("/edit")
    public String editMouse(Mouse mouse){

        service.updatingMouse(service.getDetails(mouse.getId()),mouse);
        return "redirect:/mouse/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteMouse(@PathVariable("id") int id){
        service.removingMouse(id);
        return "redirect:/mouse/index";
    }
}
