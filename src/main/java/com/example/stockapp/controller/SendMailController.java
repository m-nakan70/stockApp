package com.example.stockapp.controller;

import com.example.stockapp.dao.MemoForm;
import com.example.stockapp.dao.NotifyForm;
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
    private MailSender mailSender;

    public SendMailController() {
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
        @GetMapping(value="/send_notify")
        @ResponseBody
        public String sendNotify() {
//        public String sendNotify() {
            String text="消費期限が3カ月以内のストックがあります!!:";
//                    + "\n ストック名:" ;//DBから消費期限とストック名を持ってきたい
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("");// 送信元メールアドレス
            msg.setTo(""); // 送信先メールアドレス
//        msg.setCc(); //Cc用
//        msg.setBcc(); //Bcc用
            msg.setSubject("消費期限のお知らせ"); // タイトル
            msg.setText("\n--------------------------\n" + text + "\n---------------------------"); //本文

            try {
                mailSender.send(msg);
            } catch (MailException e) {
                e.printStackTrace();
            }
          return "消費期限通知";
        }
    }
