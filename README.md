# stockApp
■アプリケーションについて
家庭内のストック品管理用アプリケーション
・食品、日用品、防災備蓄品の登録ができます

■起動方法
　JDK17使用

■基本操作
・データベース
　MySQL使用、データベース名；stock_db　を作成してください
 
・買い物メモ、賞味期限が近くなったものをe-mailで通知できます
 　e-mailで通知を飛ばすには、《application.properties》 に　　spring.mail.username = "gmailアドレス"
 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　  spring.mail.password = "gmailのアプリケーションPW"　、
   《SendMailController.java》　に　msg.setFrom("");// 送信元メールアドレス
           　　　　　　　　 　　　   msg.setTo(""); // 送信先メールアドレス   の登録が必要です 
                       
 ・アプリケーション起動後、URL: http://localhost:8080/list からアクセス、登録タブからストックを登録してください
  
■実装できていませんが、買い物メモをLINEで知らせる、消費期限が近くなった品物のストック名を知らせる機能を入れたいと思っています  
  
　
