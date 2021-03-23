package com.nobj.forum.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeatailsResponse {
	
	private Integer status = 200;
	private String message = "Success!";
	private Object data = null;
	
	public DeatailsResponse(Object data) {
		this.data = data;
	}
	
}
