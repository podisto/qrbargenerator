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
	
	int saveOrUpdate(Product product);
}
