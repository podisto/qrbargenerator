/**
 * 
 */
package com.simba.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simba.dao.ProductDao;
import com.simba.model.Product;

/**
 * @author podisto
 *
 */
@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameter;

	/* (non-Javadoc)
	 * @see com.simba.dao.ProductDao#findAll()
	 */
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.simba.dao.ProductDao#saveOrUpdate(com.simba.model.Product)
	 */
	@Override
	public int saveOrUpdate(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

}
