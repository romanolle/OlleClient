package nf.co.olle.morosystems.server;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@Size(min=2,max=24)
	private String name;
	@Email
	private String email;
	@Size(min=2,max=2000)
	private String message;
	private Date created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getCreated() {
		return created;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	@Override
	public String toString() {
		return created+" - Name: "+name+", email: "+email+", message: "+message;
	}
}
