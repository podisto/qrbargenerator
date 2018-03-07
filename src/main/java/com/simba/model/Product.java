/**
 * 
 */
package com.simba.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author podisto
 *
 */
@Getter @Setter @ToString
public class Product {
	private Integer id;
	private String codeTechnique;
	private String designation;
	private String marque;
	private String modele;
	private String numeroSerie;
}
