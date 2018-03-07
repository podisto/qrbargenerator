/**
 * 
 */
package com.simba.service;

import java.util.ArrayList;
import java.util.List;

import com.simba.model.Product;

/**
 * @author podisto
 *
 */
public class ProductService {
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}
	
	public List<Product> init() {
		this.productList = new ArrayList<>();
		Product p1 = new Product();
		p1.setId(1);
		p1.setCodeTechnique("LB01/202/MAB/001");
		p1.setDesignation("REFRIGERATEUR 2 PORTES");
		p1.setMarque("SHARP");
		p1.setModele(null);
		p1.setNumeroSerie(null);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setCodeTechnique("LB01/202/MOB/001");
		p2.setDesignation("BUREAU AVEC CAISSON FIXE 3 TIROIRS + 1 BATTANT");
		p2.setModele("Bois Rouge");
		p2.setMarque(null);
		p2.setNumeroSerie(null);
		
		Product p3 = new Product();
		p3.setId(3);
		p3.setCodeTechnique("LB01/204/MAB/003");
		p3.setDesignation("DESTRUCTEUR PAPIER");
		p3.setMarque("FELLOWES");
		p3.setModele("W114");
		p3.setNumeroSerie("CRC34526");
		
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		return productList;
	}
}
