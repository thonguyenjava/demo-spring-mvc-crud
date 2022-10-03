package demo.spingmvccrud.controller;

import demo.spingmvccrud.entity.User;
import demo.spingmvccrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/")
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
        if (user.getId() != null) {
            User oldUser = userService.getUserById(user.getId());
            if (!user.getLoginId().equals(oldUser.getLoginId()) && userService.isLoginIdExists(user.getLoginId())) {
                model.addAttribute("error", "Login id already exists!");
                return mv;
            }
            if (!user.getMobileNo().equals(oldUser.getMobileNo()) && userService.isMobileNoExists(user.getMobileNo())) {
                model.addAttribute("error", "Mobile No already exists!");
                return mv;
            }
            model.addAttribute("success","Updated user "+ user.getFirstName()+" "+ user.getLastName() +" Susses!");
        } else {
            if (userService.isLoginIdExists(user.getLoginId())) {
                model.addAttribute("error", "Login id already exists!");
                return mv;
            }
            if (userService.isMobileNoExists(user.getMobileNo())) {
                model.addAttribute("error", "Mobile No already exists!");
                return mv;
            }
            model.addAttribute("success","Created user "+ user.getFirstName()+" "+ user.getLastName() +" Susses!");
        }
        model.addAttribute("edit",true);
        userService.saveUser(user);
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
            return mv;
        }
        List<User> users = userService.deleteUser(Long.valueOf(id));
        model.addAttribute("users", users);
        model.addAttribute("success","Delete user successful!");
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
            return mv;
        }
        User user = userService.getUserById(Long.valueOf(id));
        model.addAttribute("user", user);
        model.addAttribute("edit",true);
        mv.setViewName("createUser");
        return mv;
    };

}
