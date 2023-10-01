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

@Service
public class StockAppDao{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    StockAppDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTimestamp);

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
                        row.get("uby").toString(),
                        row.get("createdAtDATETIME").toString(),
                        row.get("updatedAtDATETIME").toString()))
                .toList();

        return  stockItems;
    }
    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM foodlist WHERE id = ?", id);
        return number;
    }

    public int update(StockItem stockItem){
        int number = jdbcTemplate.update("UPDATE foodlist SET  memo = ?, qty = ?, type = ?, updatedAtDATETIME = ? WHERE id= ?",
                    stockItem.memo(),
                    stockItem.qty(),
                    stockItem.updatedAtDatetime(),
                    stockItem.id());
        return number;
    }
}
