package com.example.stockapp.controller;

import com.example.stockapp.dao.DailyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DailyController {
    private final DailyDao dao;
    public record DailyItem(String id, String daily, String memo, String qty, String type){}

    private List<DailyItem> dailyItems = new ArrayList<>();

    @Autowired
    DailyController(DailyDao dao){
        this.dao = dao;
    }
//    @RequestMapping(value = "/hellod")
//    @ResponseBody
//    String hello(Model model){
//        model.addAttribute("time", LocalDateTime.now());
//        return "hello";
//    }
//    @GetMapping("/registrationd")
//    String regItems(Model model){
//        List<DailyItem>dailyItems = dao.findAll();
//        model.addAttribute("dailyList", dailyItems);
//        return "registration";
//    }
//    @GetMapping("/dailyd")
//    String daylyItems(Model model){
//        List<DailyItem>dailyItems = dao.findAll();
//        model.addAttribute("dailyList", dailyItems);
//        return "dailylist";
//    }
//
//    @GetMapping("/emergencyd")
//    String emergencyItems(Model model){
//        List<DailyItem>emergencyItems = dao.findAll();
//        model.addAttribute("dailyList", dailyItems);
//        return "emergency";
//    }
    @GetMapping("/dailyadd")
    String addItem(@RequestParam("daily") String daily,
                   @RequestParam("memo") String memo,
                   @RequestParam("qty") String qty,
                   @RequestParam("type") String type){
        String id = UUID.randomUUID().toString().substring(0,8);
        DailyItem item = new DailyItem(id, daily, memo, qty, type);
        dao.add(item);

        return "redirect:/dailylistd";
    }
    @GetMapping("/dailylistd")
    String dailyItems(Model model){
        List<DailyItem> dailyItems = dao.findAll();
        model.addAttribute("dailyList", dailyItems);
        return "daily";
    }
    @GetMapping("/dailydelete")
    String deleteItem(@RequestParam("id") String id){
        dao.delete(id);
        return "redirect:/dailylistd";
    }
    @GetMapping("/dailyupdate")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("daily") String daily,
                      @RequestParam("memo") String memo,
                      @RequestParam("qty") String qty,
                      @RequestParam("type") String type){
        DailyItem dailyItem = new DailyItem(id, daily, memo, qty, type);
        dao.update(dailyItem);
        return "redirect:/dailylistd";
    }
}


