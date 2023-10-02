package com.example.stockapp.dao;

import com.example.stockapp.controller.DailyController;
import com.example.stockapp.controller.DailyController.DailyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DailyDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    DailyDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(DailyItem dailyItem){
        SqlParameterSource param =new BeanPropertySqlParameterSource(dailyItem);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dailylist");
        insert.execute(param);
    }
    public List<DailyItem> findAll() {
        String query = "SELECT * FROM dailylist";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<DailyController.DailyItem> dailyItems = result.stream()
                .map((Map<String, Object> row) -> new DailyController.DailyItem(
                        row.get("id").toString(),
                        row.get("daily").toString(),
                        row.get("memo").toString(),
                        row.get("qty").toString(),
                        row.get("type").toString()))
                .toList();

        return  dailyItems;
    }
    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM dailylist WHERE id = ?", id);
        return number;
    }

    public int update(DailyItem dailyItem){
        int number = jdbcTemplate.update("UPDATE dailylist SET daily = ?, memo = ?, qty = ?, type = ?  WHERE id= ?",
                dailyItem.daily(),
                dailyItem.memo(),
                dailyItem.qty(),
                dailyItem.type(),
                dailyItem.id());
        return number;
    }
}
