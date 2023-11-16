package com.example.stockapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class HomeRestController {

   record StockItem <integer>(String id, String stock, String memo, String qty, String type ){}
//    , integer type, String regi_day, String up_day
    private List<StockItem> stockItems = new ArrayList<>();

    @GetMapping("/restadd")
    String addItem(@RequestParam("stock") String stock,
                  @RequestParam("memo") String memo,
                  @RequestParam("qty") String qty,
                  @RequestParam("type") String type){
          String id = UUID.randomUUID().toString().substring(0,8);
          StockItem item = new StockItem(id, stock, memo, qty, type);
          stockItems.add(item);

          return "ストックを追加しました";
    }
    @GetMapping("/restlist")
    String listItems(){
        String result = stockItems.stream()
                .map(StockItem::toString)
                .collect(Collectors.joining(", "));
        return result;
    }
}

