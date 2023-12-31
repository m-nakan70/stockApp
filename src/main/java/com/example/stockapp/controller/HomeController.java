package com.example.stockapp.controller;

import com.example.stockapp.dao.StockAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * 食料品に関するクラス
 * */
@Controller
public class HomeController {
    public final StockAppDao dao;

    public record StockItem(String id, String stock, String memo, String qty, String type, String subtype, String uby){
    }
//    String created_at, String updated_at
    public List<StockItem> stockItems = new ArrayList<>();

    @Autowired
    HomeController(StockAppDao dao){
        this.dao = dao;
    }

    @GetMapping("/registration")
    String regItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "registration";
    }
    @GetMapping("/dailylist")
    String daylyItems(Model model){
        List<StockItem>stockItems = dao.findAll();
        model.addAttribute("stockList", stockItems);
        return "dailylist";
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
                   @RequestParam("subtype") String subtype,
                   @RequestParam("uby") String uby){
        String id = UUID.randomUUID().toString().substring(0,8);
        StockItem item = new StockItem(id, stock, memo, qty, type, subtype, uby);
        dao.add(item);

        return "redirect:/list";
    }
    @GetMapping("/list")
    String stockItems(Model model){
        String sql = "SELECT id FROM foodlist ORDER BY id DESC";
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
                      @RequestParam("subtype") String subtype,
                      @RequestParam("uby") String uby){

        StockItem stockItem = new StockItem(id, stock, memo, qty, type, subtype, uby);
        dao.update(stockItem);
        return "redirect:/list";
    }
}

