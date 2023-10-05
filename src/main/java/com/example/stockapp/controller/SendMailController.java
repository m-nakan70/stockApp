package com.example.stockapp.controller;

import java.util.Arrays;
import java.util.List;

import com.example.stockapp.dao.MemoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {
    @Autowired
    private MailSender mailSender;

    @RequestMapping(value="/sendmail", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> sendmail(@RequestBody MemoForm form) {
        String body ="買い物メモ: \n "+ form.getMessage();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("mio701@hotmail.com");
        msg.setTo("mio.nakanishi70@gmail.com");//適宜変更
        msg.setSubject("買い物メモ");
        msg.setText("買って来て欲しいもの\n\n--------------------------\n" + body + "\n---------------------------");
        mailSender.send(msg);
        return Arrays.asList("OK");
    }
}
