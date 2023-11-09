package cse.web;

public class User{
	public String email;
	public String username;
	public int registration;
	public String password;
	public String role;
	
	
	public User(String email, String username, int registration, String password, String role) {
		this.email=email;
		this.username=username;
		this.registration=registration;
		this.password=password;
		this.role=role;
	}
}