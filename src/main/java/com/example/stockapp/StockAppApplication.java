package com.example.stockapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableAsync
public class StockAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);
	}
}

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.stream.Collectors;

//class LineNotify {
//	@GetMapping(value="/send_notify")
//	public void LineNotify() {
//		LineNotify lineNotify = new LineNotify(""); // LINE Notifyのアクセストークン(適宜変更)
//		lineNotify.notify("消費期限が3カ月以内のストックがあります");
//		System.out.println("javaからlineへ通知しました。");
//	}
//
//	private final String token;
//	public LineNotify(String token) {
//		this.token = token;
//	}
//
//	public void notify(String message) {
//		HttpURLConnection connection = null;
//		try {
//			URL url = new URL("https://notify-api.line.me/api/notify");
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setRequestMethod("POST");
//			connection.addRequestProperty("Authorization", "Bearer " + token);
//			try (OutputStream os = connection.getOutputStream();
//				 PrintWriter writer = new PrintWriter(os)) {
//				writer.append("message=").append(URLEncoder.encode(message, "UTF-8")).flush();
//				try (InputStream is = connection.getInputStream();
//					 BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
//					String res = r.lines().collect(Collectors.joining());
//					if (!res.contains("\"message\":\"ok\"")) {
//						System.out.println(res);
//					}
//				}
//			}
//		} catch (Exception ignore) {
//		} finally {
//			if (connection != null) {
//				connection.disconnect();
//			}
//		}
//	}
//}
//
