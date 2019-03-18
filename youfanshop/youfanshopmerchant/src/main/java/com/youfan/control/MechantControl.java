package com.youfan.control;

import com.youfan.model.Mechant;
import com.youfan.service.MechantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Controller
public class MechantControl {

    @Autowired
    MechantService mechantService;

    @RequestMapping(value = "/insertMechant",method = RequestMethod.POST)
    public void insertMechant(Mechant mechant){
        mechantService.insertMechant(mechant);
    }

    @RequestMapping(value = "/toinsertMechant",method = RequestMethod.GET)
    public String toinsertMechant(){
        return "mechantregister";
    }

    @RequestMapping(value = "/toupateMechant",method = RequestMethod.GET)
    public String toupateMechant(Model model,int id){
        Mechant mechant = mechantService.findMechantbyid(id);
        model.addAttribute("mechant",mechant);
        return "mechantupdate";
    }

    @RequestMapping(value = "/upateMechant",method = RequestMethod.POST)
    public void upateMechant(Mechant mechant){
        mechantService.upateMechant(mechant);
    }

    @RequestMapping(value = "/findMechantbyid",method = RequestMethod.GET)
    public String findMechantbyid(Model model,int id){
        Mechant mechant = mechantService.findMechantbyid(id);
        model.addAttribute("mechant",mechant);
        return "mechantview";
    }

    @RequestMapping(value = "/upateMechantstatus",method = RequestMethod.POST)
    public void upateMechantstatus(int status,int id){
       mechantService.upateMechantstatus(status,id);
    }

    @RequestMapping(value = "/toupateMechantstatus",method = RequestMethod.GET)
    public String toupateMechantstatus(int id,Model model){
        Mechant mechant = mechantService.findMechantbyid(id);
        model.addAttribute("mechant",mechant);
        return "mechantaudit";
    }

    @RequestMapping(value = "/toupatesoldout",method = RequestMethod.GET)
    public String toupatesoldout(Model model ,int id){
        Mechant mechant = mechantService.findMechantbyid(id);
        model.addAttribute("mechant",mechant);
        return "mechantsoldout";
    }

    @RequestMapping(value = "/upatesoldout",method = RequestMethod.POST)
    public void upatesoldout(int status,int id){
        Mechant mechant = new Mechant();
        mechant.setId(id);
        mechant.setSoldout(status);
        mechantService.upatesoldout(status,id);
    }

}
