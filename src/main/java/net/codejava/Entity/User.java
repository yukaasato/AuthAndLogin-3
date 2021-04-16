package net.codejava.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {
	
	
	@Id//The @Id and @GeneratedValue annotations map the field id to the primary key column of the table.	@Idおよび@GeneratedValueアノテーションは、フィールドIDをテーブルの主キー列にマップします。
	@Column(name = "ID")		
	@NotNull
	private int id;
	
	@Column(name = "CREATED_AT")
	private Date  created_at;
	
	@Column(name = "UPDATED_AT")
	private Date  updated_at;
	
	
	@Column(name = "AVTIVE_FLAG")
	private Boolean active_flag;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Date getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Date update_at) {
		this.updated_at = update_at;
	}


	public Boolean getActive_flag() {
		return active_flag;
	}


	public void setActive_flag(Boolean active_flag) {
		this.active_flag = active_flag;
	}
	
	
	
}
