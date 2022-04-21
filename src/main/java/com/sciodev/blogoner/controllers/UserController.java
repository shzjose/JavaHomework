package com.sciodev.blogoner.controllers;

import com.sciodev.blogoner.models.dao.IUserDAO;
import com.sciodev.blogoner.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    @Qualifier("UserDAOJPA")
    private IUserDAO userDAO;

    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("title", "Users List");
        model.addAttribute("users", userDAO.findAll());
        return "list-users";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("title", "User Form");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(User user) {
        userDAO.save(user);
        return "redirect:list-users";
    }
}
