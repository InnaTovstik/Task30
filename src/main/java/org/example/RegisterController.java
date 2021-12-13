package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class RegisterController {
    @GetMapping
    public String create() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String output(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("user_first_name");
        String lastName = request.getParameter("user_last_name");
        String gender = request.getParameter("user_gender");
        String password = request.getParameter("user_pass");
        String check = request.getParameter("check");

        if (firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() ||
                password.isEmpty()) {
            model.addAttribute("error", "Error. Enter the data to line");
            return "error";
        } else if (password.length() < 6) {
            model.addAttribute("error", "Error. Enter the password more than 6 characters");
            return "error";
        } else if (check.isEmpty()) {
            model.addAttribute("error", "Сonfirm data processing!");
            return "error";
        } else if (firstName.chars().anyMatch(Character::isDigit) || lastName.chars().anyMatch(Character::isDigit)) {
            model.addAttribute("error", "Error. First and last name contain numbers");
            return "error";
        } else {
            model.addAttribute("user_first_name", firstName);
            model.addAttribute("user_last_name", lastName);
            return "welcome";
        }
    }
}