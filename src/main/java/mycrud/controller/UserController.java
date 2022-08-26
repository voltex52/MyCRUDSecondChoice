package mycrud.controller;

import mycrud.model.User;
import mycrud.service.UserService;
import mycrud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "user-form";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user-form";

        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.find(id));
        return "user-update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "user-update";

        userService.update(id, user);
        return "redirect:/";
    }

    @RequestMapping(value="/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.delete(id);
        return modelAndView;
    }

}
