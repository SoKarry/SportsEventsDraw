package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.Repositories.EventRepo;
import com.sp.SportsEventsDraw.Repositories.GameRepo;
import com.sp.SportsEventsDraw.Repositories.PlayerRepo;
import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import com.sp.SportsEventsDraw.domain.Player;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

@Controller
public class MainController {
    @Autowired
    private EventRepo EventRepo;
    @Autowired
    private PlayerRepo PlayerRepo;
    @Autowired
    private GameRepo GameRepo;
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
            @RequestParam String name, Model model, String pl_names){
        Event event = new Event(name, user);
        EventRepo.save(event);

        Stack<String> pl_split = new Stack<String>();
        pl_split.addAll(Arrays.asList(pl_names.split("\\r?\\n")));

        for (String pl_inp : pl_split) {
            Player player = new Player(pl_inp);
            PlayerRepo.save(player);
        }
        Collections.shuffle(pl_split);
        while (!pl_split.isEmpty()){
            Player player1 = PlayerRepo.findByPlname(pl_split.pop());
            Player player2 = PlayerRepo.findByPlname(pl_split.pop());
            Game game = new Game(event, player1, player2);
            GameRepo.save(game);
        }
        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "add_event";
    }

}