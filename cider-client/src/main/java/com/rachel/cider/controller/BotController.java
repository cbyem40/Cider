package com.rachel.cider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Created by rachel.tung on 13/05/2017.
 */

@RequestMapping("/bot")
@Controller
public class BotController {

    @GetMapping("/weblogin")
    public String weblogin(Map<String,Object > model){
        model.put("title", "Oh Dios, que calor!");
        model.put("content", "Let's try some facebook function");
        return "webLogin";
    }

    @GetMapping("/backendlogin")
    public String backendlogin(Map<String,Object > model){
        return "backendLogin";
    }


    @GetMapping("/getUpdate")
    @ResponseBody
    public void getUpdate(){

    }

}
