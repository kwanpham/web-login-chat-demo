package kwan.org.webloginchatdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by https://github.com/kwanpham
 */
@Controller
public class LoginController {

    @GetMapping({"/" , "/login"})
    public String login(@RequestParam(required = false) String message , Model model) {
        return "/login";
    }
}
