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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "index";
    }
    @GetMapping("/add_event")
    public String add(@AuthenticationPrincipal User user, Model model) {
        Iterable<Event> events = user.getEvents();
//        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "add_event";
    }
    @PostMapping("/add_event")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String pl_names,
                      @Valid Event event, BindingResult bindingResult, Model model){
        event.setOwner(user);
        Stack<String> pl_split = new Stack<String>();
        pl_split.addAll(Arrays.asList(pl_names.split("\\r?\\n")));
        if ((pl_split.size() > 0) && !((pl_split.size() & (pl_split.size() - 1)) == 0)){
            model.addAttribute("pl_namesError", "Количество участников розыгрыша олимпийской системы жеребьёвки обязательно должно быть степенью двойки!");
        }
        else if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ErrorController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("event", event);
        }
        else {
//        Event event = new Event(name, user);
            EventRepo.save(event);
            for (String pl_inp : pl_split) {
                Player player = new Player(pl_inp);
                PlayerRepo.save(player);
            }
            Collections.shuffle(pl_split);
            while (!pl_split.isEmpty()) {
                Player player1 = PlayerRepo.findByPlname(pl_split.pop());
                Player player2 = PlayerRepo.findByPlname(pl_split.pop());
                Game game = new Game(event, player1, player2);
                GameRepo.save(game);
            }
            model.addAttribute("event", null);
            model.addAttribute("pl_namesError", null);
        }
        Iterable<Event> events = user.getEvents();
//        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        return "add_event";
    }
    @GetMapping("/event/{event}")
    public String aboutEvent(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Event event,
            Model model
    ) {
        Set<Game> games = event.getGames();
        User user = event.getOwner();
        model.addAttribute("games", games);
        model.addAttribute("event", event);
        model.addAttribute("isCurrentUser", user.getId().equals(currentUser.getId()));
//        add pl_names for edit
        return "event";
    }

    @PostMapping("/event/{event}")
    public String editEvent(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Event event,
            @RequestParam("name") String name,
            @RequestParam("pl_names") String pl_names
    ) throws IOException {
        if (event.getOwner().equals(currentUser)) {
            if (StringUtils.hasText(name)) {
                event.setName(name);
            }
            EventRepo.save(event);
        }

        return "redirect:/event/" + event.getId();
    }

}