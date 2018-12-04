package com.erasmus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VotingController {

//    @GetMapping("/votes")
//    private String getVotes() {
//        return "";
//    }

    @GetMapping("/")
    private String index() {
        return "index";
    }


}
