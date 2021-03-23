package com.nobj.forum.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
	
	@NotBlank
	@Size(min = 4, max = 32, message = "Username must be between 4 and 32 characters long")
	private String username;
	
	@NotNull
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters long")
	private String password;
	
	private Set<String> roles;

}