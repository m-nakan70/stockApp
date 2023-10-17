package com.example.stockapp;

import com.example.stockapp.controller.NotifyTimer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.Timer;
@SpringBootApplication
@EnableScheduling
public class StockAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);
	}
}

