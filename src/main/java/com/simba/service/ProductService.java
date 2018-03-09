/**
 * 
 */
package com.simba.service;

import java.util.HashMap;
import java.util.List;

import com.simba.model.Product;

/**
 * @author podisto
 *
 */
public interface ProductService {
List<Product> findAll();
	
	int save(Product product);
	
	int saveList(List<Product> products);
	
	HashMap<String, Object> loadExcelData(String filename);
}
