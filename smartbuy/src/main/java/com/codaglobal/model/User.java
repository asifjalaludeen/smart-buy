package com.codaglobal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codaglobal.util.UserRole;

/**
 * @author Asif Jalaludeen
 *
 */
@Entity
@Table(name = "sb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email_id", unique = true)
	private String emailId;
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	@Column(name = "user_name", unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "pincode")
	private Long pincode;
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	public User() {
	}
	/**
	 * @param name
	 * @param emailId
	 * @param phoneNumber
	 * @param username
	 * @param password
	 * @param role
	 */
	public User(String name, String emailId, String phoneNumber, String username, String password, UserRole role) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the pincode
	 */
	public Long getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
}
