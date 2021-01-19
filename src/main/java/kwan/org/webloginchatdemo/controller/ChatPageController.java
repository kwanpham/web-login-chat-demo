package kwan.org.webloginchatdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by https://github.com/kwanpham
 */
@Controller
public class ChatPageController {

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}
