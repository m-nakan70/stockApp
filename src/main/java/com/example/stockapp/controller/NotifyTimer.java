package com.example.stockapp.controller;
import com.example.stockapp.controller.SendMailController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import lombok.Getter;
import lombok.Setter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//public class NotifyTimer {
//
//        private static final Logger log = LoggerFactory.getLogger(NotifyTimer.class);
//
//        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//        @Scheduled(fixedRate = 5000)
//        public void reportCurrentTime() {
//            log.info("The time is now {}", dateFormat.format(new Date()));
//        }
//    }

@Component
public class NotifyTimer {

    static int i = 1;

    @Scheduled(cron = "${cron.cron1}")
    public void doSomething() {
        System.out.println(i + "回目タスク開始" + new Date());
        i = i + 1;
        System.out.println(i + "回目タスク開始" + new Date());
    }
}
//    public void sendNotify() {
//
//    }

//    @Scheduled(cron = "${cron.cron2}")
//    public void sendnotify() {
//        String message;
//    }
//}
//        //日時を格納するためのDateクラスを宣言(現在時刻)
//        Date date = new Date();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        String exp = new exp();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date exp = dateFormat.parse(exp);
//
//        Date exp =
//
//
//
////        //Date型の持つ日時の4年後を表示(日時の加算)
//        calendar.add(Calendar.YEAR, 4);
//        date = calendar.getTime();
//        System.out.println(date);
//
//        //Date型の持つ日時の3日前を表示(日時の減算)
//        calendar.add(Calendar.DAY_OF_MONTH, -3);
//        date = calendar.getTime();
//        System.out.println(date);
//
//    }



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
//@Getter
//@Setter
//class sendNotify {
//    String exp;
//}
