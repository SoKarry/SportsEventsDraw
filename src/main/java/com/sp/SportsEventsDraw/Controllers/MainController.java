package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.Repositories.EventRepo;
import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private EventRepo EventRepo;
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/add_event")
    public String add(Model model) {
        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "add_event";
    }
    @PostMapping("/add_event")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String name, Model model){
        Event event = new Event(name, user);
        EventRepo.save(event);
        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "add_event";
    }

}