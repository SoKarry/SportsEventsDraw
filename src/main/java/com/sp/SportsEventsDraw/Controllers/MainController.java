package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.Repositories.*;
import com.sp.SportsEventsDraw.domain.*;
import com.sun.source.util.SourcePositions;
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
    private DrawRepo DrawRepo;
    @Autowired
    private TypeRepo TypeRepo;
    @Autowired
    private GameRepo GameRepo;
    @GetMapping("/")
    public String index(@RequestParam(required = false) Long type_id, @RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Event> events = EventRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            if (type_id != null) {
                Type type = TypeRepo.findTypeById(type_id);
                model.addAttribute("type", type);
                events = EventRepo.findEventsByTypeAndNameContaining(type, filter);
            } else {
                events = EventRepo.findEventsByNameContaining(filter);
            }
        }
        else {
            if (type_id != null) {
                Type type = TypeRepo.findTypeById(type_id);
                model.addAttribute("type", type);
                events = EventRepo.findEventsByType(type);
        }
            }
        Iterable<Type> types = TypeRepo.findAll();
        model.addAttribute("events", events);
        model.addAttribute("filter", filter);
        model.addAttribute("types", types);
        return "index";
    }
    @GetMapping("/add_event")
    public String add(@AuthenticationPrincipal User user, Model model) {
        Set<Event> events = EventRepo.findEventsByOwnerId(user.getId());
        Iterable<Type> types = TypeRepo.findAll();
        model.addAttribute("events", events);
        model.addAttribute("types", types);
        model.addAttribute("draws", DrawRepo.findAll());
        return "add_event";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @PostMapping("/add_event")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String pl_names,
                      @RequestParam String type_name,
                      @RequestParam String draw_name,
                      @Valid Event event, BindingResult bindingResult, Model model){
        event.setOwner(user);
        String link = "add_event";
        Stack<String> pl_split = new Stack<String>();
        Stack<Player> playersList = new Stack<Player>();
        pl_split.addAll(Arrays.asList(pl_names.split("\\r?\\n")));
        Type type = TypeRepo.findByName(type_name);
        Draw draw = DrawRepo.findByName(draw_name);
        event.setType(type);
        event.setDraw(draw);
        if (event.getDraw().getId()==2 && pl_split.size()<3){
            model.addAttribute("pl_namesError", "Количество участников круговой системы жеребьёвки должно быть >= 3!");
        }
        else if ((event.getDraw().getId()==1) && (((pl_split.size() > 0) && !((pl_split.size() & (pl_split.size() - 1)) == 0)) || (pl_split.size()<4))){
            model.addAttribute("pl_namesError", "Количество участников олимпийской системы жеребьёвки обязательно должно быть >= 4 и быть степенью двойки!");
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
            //Если олимпийская
            if (event.getDraw().getId() == 1) {
                while (!playersList.isEmpty()) {
                    Player player1 = playersList.pop();
                    Player player2 = playersList.pop();
                    Game game = new Game(event, player1, player2);
                    GameRepo.save(game);
                }
                //Если круговая
            } else if (event.getDraw().getId() == 2) {
                for (Player pl1 : playersList) {
                    for (Player pl2 : playersList) {
                        if (pl1 != pl2) {
                            Game game = new Game(event, pl1, pl2);
                            GameRepo.save(game);
                        }
                    }
                }
            }
            model.addAttribute("event", null);
            model.addAttribute("game", null);
            model.addAttribute("pl_namesError", null);
            model.addAttribute("pl_split", null);
            link = "redirect:/add_event";
        }
//        Set<Event> events = user.getEvents();
        Set<Event> events = EventRepo.findEventsByOwnerId(user.getId());
//        System.out.println(events);
//        Iterable<Event> events = EventRepo.findAll();
        model.addAttribute("events", events);
        model.addAttribute("draws", DrawRepo.findAll());
        model.addAttribute("types", TypeRepo.findAll());
        model.addAttribute("pl_split", pl_split);
        return link;
    }
    @GetMapping("/event/{event}")
    public String aboutEvent(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Event event,
            Model model
    ) {
        Set<Game> games = GameRepo.findByEvownerOrderById(event);
        if (event.getDraw().getId() == 2) {
            Game game = games.iterator().next();
            Set<Game> games1 = GameRepo.findByPlayer1IdOrderById(game.getPlayer1().getId());
            List<Player> players = new ArrayList<Player>();;
            players.add(game.getPlayer1());
            for (Game gm : games1) {
                players.add(gm.getPlayer2());
            }
            model.addAttribute("players", players);
        }
        Iterable<Type> types = TypeRepo.findAll();
        User user = event.getOwner();
        model.addAttribute("games", games);
        model.addAttribute("event", event);
        model.addAttribute("types", types);
        model.addAttribute("draws", DrawRepo.findAll());
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
            @RequestParam String type_name,
            @RequestParam("pl_names") String pl_names
    ) throws IOException {
        if (event.getOwner().equals(currentUser)) {
            if (StringUtils.hasText(name)) {
                Type type = TypeRepo.findByName(type_name);
                event.setName(name);
                event.setType(type);
                event.setPl_names(pl_names);
            }
            EventRepo.save(event);
        }
        return "redirect:/event/" + event.getId();
    }

    @GetMapping("/event/del/{event}")
    public String delEvent(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Event event
    ) throws IOException {
        if (event.getOwner().equals(currentUser) || currentUser.isAdmin()) {
            EventRepo.deleteById(event.getId());
        }
        return "redirect:/";
    }

}