package demo.spingmvccrud.controller;

import demo.spingmvccrud.entity.User;
import demo.spingmvccrud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping(path = "/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    
    @GetMapping("/listUsers")
    public ModelAndView allUsersView(ModelMap model) {
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        mv.setViewName("allUsers");
        return mv;
    };

    @GetMapping
    public String createUserView(ModelMap model) {
        model.addAttribute("user",new User());
        return "createUser";
    };

    @PostMapping
    public ModelAndView saveUser(@Validated @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("createUser");
        String success;
        String error = null;
        if (user.getId() != null) {
            User oldUser = userService.getUserById(user.getId());
            if (!user.getLoginId().equals(oldUser.getLoginId()) && userService.isLoginIdExists(user.getLoginId())) {
                error = "Login id already exists!";
            }
            if (!user.getMobileNo().equals(oldUser.getMobileNo()) && userService.isMobileNoExists(user.getMobileNo())) {
                error = "Login id already exists!";
            }
            success = "Updated user "+ user.getFirstName()+" "+ user.getLastName() +" Susses!";
        } else {
            if (userService.isLoginIdExists(user.getLoginId())) {
                error = "Login id already exists!";
            }
            if (userService.isMobileNoExists(user.getMobileNo())) {
                error = "Mobile No already exists!";
            }
            success = "Created user "+ user.getFirstName()+" "+ user.getLastName() +" Susses!";
        }
        if (error != null) {
            model.addAttribute("error", error);
            log.error(error);
            return mv;
        }
        userService.saveUser(user);
        model.addAttribute("edit",true);
        model.addAttribute("success", success);
        log.info(success);
        return mv;
    };

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable String id, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("allUsers");
        if (!userService.isUserExists(Long.valueOf(id))){
            model.addAttribute("error","User not found!");
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            log.error("User not found!");
            return mv;
        }
        List<User> users = userService.deleteUser(Long.valueOf(id));
        model.addAttribute("users", users);
        model.addAttribute("success","Delete user successful!");
        log.info("Delete user successful: "+id);
        return mv;
    };

    @GetMapping("/{id}")
    public ModelAndView userDetail(@PathVariable String id, ModelMap model) {
        ModelAndView mv = new ModelAndView();
        if (!userService.isUserExists(Long.valueOf(id))){
            mv.setViewName("allUsers");
            model.addAttribute("error","User not found!");
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            log.error("User not found!");
            return mv;
        }
        User user = userService.getUserById(Long.valueOf(id));
        model.addAttribute("user", user);
        model.addAttribute("edit",true);
        mv.setViewName("createUser");
        log.info("Get user by id success: "+user.getLoginId());
        return mv;
    };

}
