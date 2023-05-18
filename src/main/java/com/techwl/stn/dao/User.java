package com.techwl.stn.dao;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contributors")
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	@NotNull
	private String name;

	@Column(name = "pwd")
	@NotNull
	private String password;

	@Column
	@NotNull
	private String email;

	@Column
	@NotNull
	private String mobile;
	
	@Column(name = "created_date")
	@NotNull
	private Date createdDate;

	@Column
	private Blob avatar;

	@Column
	private boolean active;
//	
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//	private Set<ContributionDetails> contributions; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Blob getAvatar() {
		return avatar;
	}

	public void setAvatar(Blob avatar) {
		this.avatar = avatar;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}
