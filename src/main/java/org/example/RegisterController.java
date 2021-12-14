package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class RegisterController {

    final int MIN_CHAR_IN_PASS = 6;

    @GetMapping
    public String create() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String output(@RequestParam ("user_first_name") String firstName,
                         @RequestParam("user_last_name") String lastName,
                         @RequestParam("user_gender") String gender,
                         @RequestParam("user_pass") String password,
                         @RequestParam("check") String check, Model model) {

        String rezult = "error";
        if (firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Error. Enter the data to line");
        } else if (password.length() < MIN_CHAR_IN_PASS) {
            model.addAttribute("error", "Error. Enter the password more than 6 characters");
        } else if (check.isEmpty()) {
            model.addAttribute("error", "Сonfirm data processing!");
        } else if (firstName.chars().anyMatch(Character::isDigit) || lastName.chars().anyMatch(Character::isDigit)) {
            model.addAttribute("error", "Error. First and last name contain numbers");
        } else {
            model.addAttribute("user_first_name", firstName);
            model.addAttribute("user_last_name", lastName);
            rezult = "welcome";
        }
        return rezult;
    }
}