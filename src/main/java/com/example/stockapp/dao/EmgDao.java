package com.example.stockapp.dao;

import com.example.stockapp.controller.EmgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class EmgDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    EmgDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(EmgController.EmgItem emgItem){
        SqlParameterSource param =new BeanPropertySqlParameterSource(emgItem);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("emglist");
        insert.execute(param);
    }
    public List<EmgController.EmgItem> findAll() {
        String query = "SELECT * FROM emglist ORDER BY type, exp, times, datet";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<EmgController.EmgItem> emgItems = result.stream()
                .map((Map<String, Object> row) -> new EmgController.EmgItem(
                        row.get("id").toString(),
                        row.get("stock").toString(),
                        row.get("memo").toString(),
                        row.get("qty").toString(),
                        row.get("type").toString(),
                        row.get("exp").toString()))
                .toList();

        return  emgItems;
    }
    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM emglist WHERE id = ?", id);
        return number;
    }

    public int update(EmgController.EmgItem emgItem){
        int number = jdbcTemplate.update("UPDATE emglist SET stock = ?, memo = ?, qty = ?, type = ?, exp = ?  WHERE id= ?",
                emgItem.stock(),
                emgItem.memo(),
                emgItem.qty(),
                emgItem.type(),
                emgItem.exp(),
                emgItem.id());
        return number;
    }
}


