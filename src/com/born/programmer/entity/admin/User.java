package com.born.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
  * @author 15188
  *
 */
@Component//Mybatis或者Strusts2在传参时会自动创建实体类对象进行封装，不需要加该注解
public class User {
	/**
	 * 用户id，设置自增
	 */
	private Long id;
	/**
	 * 用户名，登录名
	 */
	private String username;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 所属角色id
	 */
	private Long roleId;
	/**
	 * 头像照片地址
	 */
	private String photo;
	/**
	 * 性别0,代表未知，1代表男，2代表女
	 */
	private int sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 家庭住址
	 */
	private String address;
	
	
	public User(Long id, String username, String password, Long roleId, String photo, int sex, Integer age,
			String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.photo = photo;
		this.sex = sex;
		this.age = age;
		this.address = address;
	}
	
	
		
	public User() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + sex;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (sex != other.sex)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roleId=" + roleId
				+ ", photo=" + photo + ", sex=" + sex + ", age=" + age + ", address=" + address + "]";
	}

	
	
}
