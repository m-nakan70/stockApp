package com.example.stockapp.dao;

import com.example.stockapp.controller.HomeController;
import com.example.stockapp.controller.HomeController.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class StockAppDao{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    StockAppDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    final Function<StockItem, String> byType = stockItem -> stockItem.gettype();
    final Function<StockItem, String> byTime = stockItem -> stockItem.getTime();

    public void add(StockItem stockItem){
        SqlParameterSource param =new BeanPropertySqlParameterSource(stockItem);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("foodlist");
        insert.execute(param);
    }
    public List<StockItem> findAll() {
        String query = "SELECT * FROM foodlist";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<HomeController.StockItem> stockItems = result.stream()
                .map((Map<String, Object> row) -> new HomeController.StockItem(
                        row.get("id").toString(),
                        row.get("stock").toString(),
                        row.get("memo").toString(),
                        row.get("qty").toString(),
                        row.get("type").toString(),
                        row.get("uby").toString()))
                .toList();

        List<HomeController.StockItem> sortedStockItemByTypeandTimes =
                stockItems.stream().sorted(comparing(byType).thenComparing(byTime)).collect(toList());

        return  stockItems;
    }
    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM foodlist WHERE id = ?", id);
        return number;
    }

    public int update(StockItem stockItem){
        int number = jdbcTemplate.update("UPDATE foodlist SET stock = ?, memo = ?, qty = ?, type = ?, uby = ? WHERE id= ?",
                    stockItem.stock(),
                    stockItem.memo(),
                    stockItem.qty(),
                    stockItem.type(),
                    stockItem.uby(),
                    stockItem.id());
        return number;
//        , updated_at = ?
    }
}
