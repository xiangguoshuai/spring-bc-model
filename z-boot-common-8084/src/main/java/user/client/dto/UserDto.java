package user.client.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private String username;
	private String password;
	private String token;
	private String msg;
	
}
