package net.codejava.Form;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_detail")
public class User_detailForm {
	
	
	@Id
	@Column(name = "ID")		
	@NotNull
	private int id;
	
	
	@Column(nullable = false, unique = true, length = 45,name="MAIL")
	private String email;
	
	@Column(nullable = false, length = 64,name="PASSWORD")
	private String password;
	
	@Column(name = "CREATED_AT")
	private Date  created_at;
	
	@Column(name = "UPDATED_AT")
	private Date  update_at;
	
	@Column(nullable = false,name="UUID")
	private String uuid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

	
}
