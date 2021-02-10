package com.myclass.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 9, 2021	
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReponse {
	private int status;
	private Object data;
}
