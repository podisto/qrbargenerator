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
	private String batiment;
	private String bureau;
	private String ordre;
	private String designation;
	private String marque;
	private String modele;
	private String numeroSerie;
	private String famille;
	private String codeTechnique;
	private String qrcode;
}
