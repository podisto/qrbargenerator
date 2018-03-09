/**
 * 
 */
package com.simba.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.simba.model.Product;

/**
 * @author podisto
 *
 */
public class ProductServiceData {
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}
	
	public List<Product> init() {
		this.productList = new ArrayList<>();
		Product p1 = new Product();
		p1.setId(1);
		p1.setCodeTechnique("LB01/202/MAB/001");
		p1.setDesignation("Réfrigerateur 2 portes");
		p1.setMarque("SHARP");
		p1.setModele(null);
		p1.setNumeroSerie(null);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setCodeTechnique("LB01/202/MOB/001");
		p2.setDesignation("Bureau avec caissons fixes 3 tiroires + 1 battant");
		p2.setMarque(null);
		p2.setModele("Bois Rouge");
		p2.setNumeroSerie(null);
		
		Product p3 = new Product();
		p3.setId(3);
		p3.setCodeTechnique("LB01/202/MOB/002");
		p3.setDesignation("Bureau avec caissons fixes 3 tiroires + 1 battant");
		p3.setMarque(null);
		p3.setModele("Bois Rouge");
		p3.setNumeroSerie(null);
		
		Product p4 = new Product();
		p4.setId(4);
		p4.setCodeTechnique("LB01/202/MOB/003");
		p4.setDesignation("Bureau");
		p4.setMarque(null);
		p4.setModele("Laqué Blanc");
		p4.setNumeroSerie(null);
		
		Product p5 = new Product();
		p5.setId(5);
		p5.setCodeTechnique("LB01/202/MOB/004");
		p5.setDesignation("Bureau Président + Retour");
		p5.setMarque(null);
		p5.setModele(null);
		p5.setNumeroSerie(null);
		
		Product p6 = new Product();
		p6.setId(6);
		p6.setCodeTechnique("LB01/202/MOB/005");
		p6.setDesignation("Fauteuil Président Roulant et Pivotant avec accoudoires");
		p6.setMarque(null);
		p6.setModele(null);
		p6.setNumeroSerie(null);
		
		Product p7 = new Product();
		p7.setId(7);
		p7.setCodeTechnique("LB01/202/MOB/006");
		p7.setDesignation("Fauteuil visiteur fixe avec accoudoires");
		p7.setMarque(null);
		p7.setModele("Cadre métallique");
		p7.setNumeroSerie(null);
		
		Product p8 = new Product();
		p8.setId(8);
		p8.setCodeTechnique("LB01/202/MOB/007");
		p8.setDesignation("Fauteuil visiteur fixe avec accoudoires");
		p8.setMarque(null);
		p8.setModele("Cadre métallique");
		p8.setNumeroSerie(null);
		
		Product p9 = new Product();
		p9.setId(9);
		p9.setCodeTechnique("LB01/203/MAB/001");
		p9.setDesignation("Climatiseur Split Système");
		p9.setMarque("TCL");
		p9.setModele(null);
		p9.setNumeroSerie(null);
		
		Product p10 = new Product();
		p10.setId(10);
		p10.setCodeTechnique("LB01/203/MAB/002");
		p10.setDesignation("Climatiseur Split Système");
		p10.setMarque("TCL");
		p10.setModele(null);
		p10.setNumeroSerie(null);
		
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		productList.add(p4);
		productList.add(p5);
		productList.add(p6);
		productList.add(p7);
		productList.add(p8);
		productList.add(p9);
		productList.add(p10);
		
		return productList;
	}
}
