/**
 * 
 */
package com.simba.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author podisto
 *
 */
@Getter @Setter @AllArgsConstructor
public class JsonResponse {
	private int status;
	private String message;
	private List<?> data;
}
