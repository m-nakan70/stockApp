package com.example.stockapp.controller;

import com.example.stockapp.dao.MemoForm;
import com.example.stockapp.dao.EmgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SendMailController {
        @Autowired
        private final MailSender mailSender;

        public SendMailController(MailSender mailSender) {
            this.mailSender = mailSender;
        }

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> sendmail(@RequestBody MemoForm form) {
        String body = "買い物メモ: \n " + form.getMessage();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("");
        msg.setTo("");//適宜変更
        msg.setSubject("買い物メモ");
        msg.setText("買って来て欲しいもの\n\n--------------------------\n" + body + "\n---------------------------");
        mailSender.send(msg);
        return Arrays.asList("OK");
    }

        @GetMapping("/")
        public String sendNotify() {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(""); // 送信元メールアドレス
            msg.setTo(""); // 送信先メールアドレス
//        msg.setCc(); //Cc用
//        msg.setBcc(); //Bcc用
            msg.setSubject("賞費期限のお知らせ"); // タイトル
            msg.setText("消費期限\r\nストック名"); //本文

            try {
                mailSender.send(msg);
            } catch (MailException e) {
                e.printStackTrace();
            }
            return "賞味期限通知";
        }
    }



//    @RequestMapping(value="/sendnotify1", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<String> sendnotify1(@RequestBody EmgDao exp) {
//        String body ="賞費期限のお知らせ: \n "+ exp.getDate(); stock.getStock();
//        SimpleNotifyMessage msg = new SimpleNotifyMessage();
//        msg.setFrom("mio701@hotmail.com");
//        msg.setTo("mio.nakanishi70@gmail.com");//適宜変更
//        msg.setSubject("消費期限お知らせ");
//        msg.setText("消費期限　\n\n--------------------------\n" + body + "\n---------------------------");
//        mailSender.send(msg);
//        return Arrays.asList("OK");
//    }
//}
