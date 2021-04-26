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
import javax.transaction.Transactional;
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
        Set<Event> events = user.getEvents();
//        Iterable<Event> events = EventRepo.findAll();
        System.out.println(events);
        Set<Event> events1 = EventRepo.findEventsByOwnerId(user.getId());
        model.addAttribute("events", events1);
        System.out.println("events1:");
        System.out.println(events1);
        return "add_event";
    }

    @PostMapping("/add_event")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String pl_names,
                      @Valid Event event, BindingResult bindingResult, Model model){
        event.setOwner(user);
        String link = "add_event";
        Stack<String> pl_split = new Stack<String>();
        Stack<Player> playersList = new Stack<Player>();
        pl_split.addAll(Arrays.asList(pl_names.split("\\r?\\n")));
//        System.out.println(pl_split.size());
        if (((pl_split.size() > 0) && !((pl_split.size() & (pl_split.size() - 1)) == 0)) || (pl_split.size()<2)){
            model.addAttribute("pl_namesError", "Количество участников олимпийской системы жеребьёвки обязательно должно быть >= 2 и быть степенью двойки!");
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
                playersList.add(player);
            }
            Collections.shuffle(playersList);
            System.out.println(playersList);
            while (!playersList.isEmpty()) {
                Player player1 = playersList.pop();
                Player player2 = playersList.pop();
                Game game = new Game(event, player1, player2);
                GameRepo.save(game);
            }
            model.addAttribute("event", null);
            model.addAttribute("game", null);
            model.addAttribute("pl_namesError", null);
//            model.addAttribute("pl_split", null);
            link = "redirect:/add_event";
        }
//        Set<Event> events = user.getEvents();
        Set<Event> events1 = EventRepo.findEventsByOwnerId(user.getId());
        System.out.println("post: ");
//        System.out.println(events);
//        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events1);
        model.addAttribute("pl_split", pl_split);
        return link;
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
        model.addAttribute("isCurrentUser", user.equals(currentUser));
//        Set<String> playersNames = new HashSet<String>();
//        for (Game g : games) {
//            playersNames.add(g.getPlayer1Name());
//            playersNames.add(g.getPlayer2Name());
//        }
//        System.out.println(playersNames);
//        model.addAttribute("playersNames", playersNames);
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
                event.setPl_names(pl_names);
//                System.out.println(name);
            }
            EventRepo.save(event);
//            System.out.println("save name");
        }
        return "redirect:/event/" + event.getId();
    }

}