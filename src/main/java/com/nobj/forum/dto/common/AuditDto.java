package com.nobj.forum.dto.common;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AuditDto {
	
	protected Long id;
	
	protected Date createdAt;
	
	protected Date updatedAt;

}
