package com.example.stockapp.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class NotifyTimer {
    static int i = 1;

    @Scheduled(cron = "${cron.cron1}")
        public void doSomething() {
             System.out.println(i + "回目タスク開始" + new Date());
             i= i + 1;
             System.out.println(i + "回目タスク開始" + new Date());
    }

}

//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import java.util.Timer;
//import java.util.TimerTask;
//
//@Component
//public class NotifyTimer extends TimerTask {
//
//    static int i = 1;
//    public void run() {
//        try {
//
//            System.out.println("-----------------------------------------");
//            System.out.println(i + "回目タスク開始" + new Date());
//
//            //3秒間停止する
//            Thread.sleep(3000);
//
//            System.out.println(i + "回目タスク終了" + new Date());
//
//            i = i + 1;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}

//    private int i = 0;
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//
//    @Scheduled(initialDelay=0, fixedDelay=3000)
//    public void execute() {
//        System.out.println("実行回数: " + ++i + ", 実行時間: " + sdf.format(new Date()));
//
//    }
//}

