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
		 * ���͂��ꂽ���[�U�[�����󂯎�� ���͂��ꂽ���[���A�h���X�����łɓo�^����Ă��Ȃ����m�F �m�F����ꂽ�ꍇ�AUUID�ƈꏏ�Ɉꎞ�e�[�u���Ƀ��[�U�[��ۑ�
		 * �F��URL�𐶐����ă��[�U�[�Ƀ��[�����t���܂��B�F��URL��/validate/id=UUID�Ƃ��Ă��܂��B
		 * ���[�U�[��URL���N���b�N�������Ƃ����ʂ���ɂ́A URL�Ɉꎞ�e�[�u���ɕۑ��������[�U�[�ƕR�Â�����ǉ����Ă����K�v������܂��B
		 * �o�b�e�B���O������\���ł��Ă��܂��ƁA���l�̈ꎞ���[�U�[��F�؂ł��Ă��܂��̂ŁA UUID���g�p���܂��B
		 * https://qiita.com/isotai/items/f810493dd192e0597f3a
		 */
		
		//boolean isMember = repo.existsByid(�I�u�W�F�N�g�H);
		
		//�J�����̑f�ޏW��
		
		//UUID
		UUID uuid =UUID.randomUUID();
		String theUuid = uuid.toString();
	
		
		//PW�̃n�b�V����
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //PW�̃n�b�V����
		String encodedPassword = encoder.encode(formData.getPassword());

		//���o�^����
		Date createdTime = new Date();
		
		//User_detail�̃C���X�^���X�𐶐�
		User_detail user_detail = new User_detail();
		
		//�Z�b�g���Ă�
		user_detail.setUuid(theUuid); 
		user_detail.setId(formData.getId());
		user_detail.setEmail(formData.getEmail());
		user_detail.setPassword(encodedPassword);  
		user_detail.setCreated_at(createdTime);	
	
		//User�̃C���X�^���X����
		User user = new User();
		user.setId(formData.getId());
		user.setCreated_at(createdTime);
		user.setActive_flag(true);
		
	
		repo.save(user_detail);
		repo2.save(user);
	
	
		
		  SimpleMailMessage message = new SimpleMailMessage();
		  
		  String email = formData.getEmail();
		  
		  message.setFrom("newadgjyj@gmail.com"); //��U�����̃��A�h ���M���H
		  message.setTo(formData.getEmail());//
		  
		  
		  String mailSubject = "���o�^�����i�F�؂��Ă��������j";//����
		  
		  String mailContent = "Sender Name: " + "\n";//���[���̒��g
		  
		  mailContent += "Sender E-mail: " + email + "\n";//���[���̒��g�@�ǉ����Ă����@�����t�t�h�c�Ƃ�������
		  
		  
		  message.setSubject(mailSubject);//�����@
		  message.setText(mailContent);//���g�@
		  
		  mailSender.send(message);	//����
		  
		 
		
		return "register_success";
	}

	
	  
	    
	       
	       
}
	