package net.codejava.Controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.codejava.Entity.User;
import net.codejava.Entity.User_detail;
import net.codejava.Form.User_detailForm;
import net.codejava.Repository.UserRepository;
import net.codejava.Repository.User_detailRepository;

@Controller
public class AuthAndLoginController {

	@Autowired
	private User_detailRepository repo;
	
	@Autowired
	private UserRepository repo2;
	
 
	 @Autowired
	 private JavaMailSender mailSender;

	
	
	@GetMapping("")
	public String viewHpmePage() {  
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User_detailForm());
		return "signup_form";
	}


	@PostMapping("/process_register")
	public String processRegistration(User_detailForm formData) {
		
		/*
		 * 入力されたユーザー情報を受け取る 入力されたメールアドレスがすでに登録されていないか確認 確認が取れた場合、UUIDと一緒に一時テーブルにユーザーを保存
		 * 認証URLを生成してユーザーにメール送付します。認証URLは/validate/id=UUIDとしています。
		 * ユーザーがURLをクリックしたことを識別するには、 URLに一時テーブルに保存したユーザーと紐づく情報を追加しておく必要があります。
		 * バッティングしたり予測できてしまうと、他人の一時ユーザーを認証できてしまうので、 UUIDを使用します。
		 * https://qiita.com/isotai/items/f810493dd192e0597f3a
		 */
		
		//boolean isMember = repo.existsByid(オブジェクト？);
		
		//カラムの素材集め
		
		//UUID
		UUID uuid =UUID.randomUUID();
		String theUuid = uuid.toString();
	
		
		//PWのハッシュ化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //PWのハッシュ化
		String encodedPassword = encoder.encode(formData.getPassword());

		//仮登録日時
		Date createdTime = new Date();
		
		//User_detailのインスタンスを生成
		User_detail user_detail = new User_detail();
		
		//セットしてく
		user_detail.setUuid(theUuid); 
		user_detail.setId(formData.getId());
		user_detail.setEmail(formData.getEmail());
		user_detail.setPassword(encodedPassword);  
		user_detail.setCreated_at(createdTime);	
	
		//Userのインスタンス生成
		User user = new User();
		user.setId(formData.getId());
		user.setCreated_at(createdTime);
		user.setActive_flag(true);
		
	
		repo.save(user_detail);
		repo2.save(user);
	
	
		
		  SimpleMailMessage message = new SimpleMailMessage();
		  
		  String email = formData.getEmail();
		  
		  message.setFrom("newadgjyj@gmail.com"); //一旦自分のメアド 送信元？
		  message.setTo(formData.getEmail());//
		  
		  
		  String mailSubject = "仮登録完了（認証してください）";//件名
		  
		  String mailContent = "Sender Name: " + "\n";//メールの中身
		  
		  mailContent += "Sender E-mail: " + email + "\n";//メールの中身　追加していく　多分ＵＵＩＤとかもここ
		  
		  
		  message.setSubject(mailSubject);//件名　
		  message.setText(mailContent);//中身　
		  
		  mailSender.send(message);	//送る
		  
		 
		
		return "register_success";
	}

	
	  
	    
	       
	       
}
	