package com.example.stockapp.controller;

import com.example.stockapp.dao.EmgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class EmgController {
    private final EmgDao dao;

    public record EmgItem(String id, String stock, String memo, String qty, String type, String exp) {
    }

    private List<EmgItem> emgItems = new ArrayList<>();

    @Autowired
    EmgController(EmgDao dao) {
        this.dao = dao;
    }

    @GetMapping("/emgadd")
    String addItem(@RequestParam("stock") String stock,
                   @RequestParam("memo") String memo,
                   @RequestParam("qty") String qty,
                   @RequestParam("type") String type,
                   @RequestParam("exp") String exp) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        EmgItem item = new EmgItem(id, stock, memo, qty, type, exp);
        dao.add(item);

        return "redirect:/emglist";
    }

    @GetMapping("/emglist")
    String emgItems(Model model) {
        List<EmgItem> emgItems = dao.findAll();
        model.addAttribute("emgList", emgItems);
        return "emergency";
    }

    @GetMapping("/emgdelete")
    String deleteItem(@RequestParam("id") String id) {
        dao.delete(id);
        return "redirect:/emglist";
    }

    @GetMapping("/emgupdate")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("stock") String stock,
                      @RequestParam("memo") String memo,
                      @RequestParam("qty") String qty,
                      @RequestParam("type") String type,
                      @RequestParam("exp") String exp) {
        EmgItem emgItem = new EmgItem(id, stock, memo, qty, type, exp);
        dao.update(emgItem);
        return "redirect:/emglist";
    }

    @GetMapping("/check_exp")
    String checkExp(Model model,@RequestParam(name = "check", required = false) String check) {
        List<EmgItem> emgItems = this.dao.checkExp(check);
        model.addAttribute("emgList", emgItems);
        return "emergency";

    }
}



