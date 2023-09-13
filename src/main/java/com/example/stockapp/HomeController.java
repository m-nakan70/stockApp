package com.example.stockapp;

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

    record StockItem(String id, String stock, String memo,Integer qty ){}
    private List<StockItem> stockItems = new ArrayList<>();
    @RequestMapping(value = "/hello")
//    @ResponseBody
    String hello(Model model){
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }
    @GetMapping("/list")
    String stockItems(Model model){
        model.addAttribute("stockList", stockItems);
        return "home";
    }
    @GetMapping("/add")
    String addItem(@RequestParam("stock") String stock,
                   @RequestParam("memo") String memo,
                   @RequestParam("qty")Integer qty){
        String id = UUID.randomUUID().toString().substring(0,8);
        StockItem item = new StockItem(id, stock, memo, qty);
        stockItems.add(item);

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


