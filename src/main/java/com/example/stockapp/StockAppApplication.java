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
				// Timerクラスのオブジェクトを作成
			Timer time = new Timer();

			System.out.println("実行開始" + new Date());

			// 一定間隔で処理を開始する
			// SampleTaskを、3秒後に、5秒間隔で実行する
			time.scheduleAtFixedRate(new NotifyTimer(), 3000, 5000);
		}
	}


