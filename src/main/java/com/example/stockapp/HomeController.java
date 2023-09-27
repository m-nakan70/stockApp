package com.example.stockapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final StockAppDao dao;
    record StockItem(String id, String stock, String memo,String qty, String type, String uby){}
    private List<StockItem> stockItems = new ArrayList<>();

    @Autowired
    HomeController(StockAppDao dao){
        this.dao = dao;
    }
    @RequestMapping(value = "/hello")
//    @ResponseBody
    String hello(Model model){
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }
    @GetMapping("/registration")
    String regItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "registration";
    }
    @GetMapping("/daily")
    String daylyItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "daily";
    }

    @GetMapping("/emergency")
    String emergencyItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "emergency";
    }
    @GetMapping("/add")
    String addItem(@RequestParam("stock") String stock,
                   @RequestParam("memo") String memo,
                   @RequestParam("qty") String qty,
                   @RequestParam("type") String type,
                   @RequestParam("uby") String uby){
        String id = UUID.randomUUID().toString().substring(0,8);
        StockItem item = new StockItem(id, stock, memo, qty, type, uby);
        dao.add(item);

        return "redirect:/list";
    }
    @GetMapping("/list")
    String stockItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "home";
    }
    @GetMapping("/delete")
    String deleteItem(@RequestParam("id") String id){
        dao.delete(id);
        return "redirect:/list";
    }
    @GetMapping("/update")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("stock") String stock,
                      @RequestParam("memo") String memo,
                      @RequestParam("qty") String qty,
                      @RequestParam("type") String type,
                      @RequestParam("uby") String uby){
        StockItem stockItem = new StockItem(id, stock, memo, qty, type, uby);
        dao.update(stockItem);
        return "redirect:/list";
    }
}


//        return """
//              <html>
//                <head><title>Hello</title></head
//                >
//                <body>
//                <h1>Hello</h1>
//                It works!<br>
//                現在時刻は%sです。
//                </body>
//              </html>
//                """.formatted(LocalDateTime.now());


