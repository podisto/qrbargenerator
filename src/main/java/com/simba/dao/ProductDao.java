/**
 * 
 */
package com.simba.dao;

import java.util.List;

import com.simba.model.Product;

/**
 * @author podisto
 *
 */
public interface ProductDao {
	List<Product> findAll();
	
	int save(Product product);
	
	int saveList(List<Product> products);
	
	List<Product> getProducts(int begin, int end);
}
