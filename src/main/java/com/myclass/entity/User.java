package com.myclass.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myclass.core.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User extends BaseEntity{

	@Id
	@Column(name = "id")
	private int id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String fullname;
	@Column(nullable = false)
	private String password;
	private String avatar;
	private String phone;
	private String address;

	@Column(name = "role_id")
	private int roleId;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<UserCourses> userCourses;

}
