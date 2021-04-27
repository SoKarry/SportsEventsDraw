package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.Repositories.TypeRepo;
import com.sp.SportsEventsDraw.Repositories.UserRepo;
import com.sp.SportsEventsDraw.domain.Type;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class TypeController {
    @Autowired
    private TypeRepo TypeRepo;

    @GetMapping("/typelist")
    public String typeList(Model model) {
        Iterable<Type> types = TypeRepo.findAll();
        model.addAttribute("types", types);
        return "typeList";
    }

    @PostMapping("/typelist")
    public String addType(Type type, Model model) {
        TypeRepo.save(type);
        return "redirect:/typelist";
    }

    @GetMapping("/typelist/{type}")
    public String aboutTypeList(@PathVariable Type type, Model model) {
        model.addAttribute("type", type);
        return "typeEdit";
    }

    @PostMapping("/typelist/{type}")
    public String saveTypeList(@PathVariable Type type, @RequestParam("name") String name, Model model) {
        type.setName(name);
        TypeRepo.save(type);
        return "redirect:/typelist";
    }
}
