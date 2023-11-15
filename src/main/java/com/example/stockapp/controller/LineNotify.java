//package com.example.stockapp.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.stream.Collectors;
//import com.example.stockapp.dao.MemoForm;
//import com.example.stockapp.dao.NotifyForm;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//public class LineNotify {
//    private String message;
//
//    @GetMapping(value="/sendnotify")
//    @ResponseBody
//    public static void linenotify() {
//        LineNotify lineNotify = new LineNotify("");
//        lineNotify.notify("LINEに通知");
//        System.out.println("賞味期限まで3カ月を切ったストックがあります！");
//    }
//
//    private final String token;
//    public LineNotify(String token) {
//        this.token = token;
//    }
//
//    public void notify(String message) {
//        HttpURLConnection connection = null;
////        MemoForm form = new MemoForm();
////        this.message = message;
//
//        try {
//            URL url = new URL("https://notify-api.line.me/api/notify");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.addRequestProperty("Authorization", "Bearer " + token);
//            try (OutputStream os = connection.getOutputStream();
//                 PrintWriter writer = new PrintWriter(os)) {
//                writer.append("message=").append(URLEncoder.encode(message, "UTF-8")).flush();
//                try (InputStream is = connection.getInputStream();
//                     BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
//                    String res = r.lines().collect(Collectors.joining());
//                    if (!res.contains("\"message\":\"ok\"")) {
//                        System.out.println(res);
//                    }
//                }
//            }
//        } catch (Exception ignore) {
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
//}
