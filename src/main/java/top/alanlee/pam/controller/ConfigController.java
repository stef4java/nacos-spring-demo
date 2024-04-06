package top.alanlee.pam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.alanlee.pam.entity.*;

import java.util.ArrayList;


import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private Enterprise enterprise;

    @RequestMapping(value = "/departments", method = GET)
    @ResponseBody
    public ArrayList<Department> departments() {
        return Departments.departments;
    }

    @RequestMapping(value = "/enterprise", method = GET)
    @ResponseBody
    public Enterprise enterprise() {
        return enterprise;
    }


}