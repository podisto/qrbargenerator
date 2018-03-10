/**
 * 
 */
package com.simba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
	
	public static final String QR_ALL = "SELECT qrpr_id, qrpr_designation, qrpr_marque, qrpr_modele, qrpr_numserie, qrpr_codetechnique, qrpr_qrcode "
			+ "FROM qr_produit ORDER BY qrpr_id ASC";
	
	public static final String QR_INSERT = "INSERT INTO qr_produit(qrpr_designation, qrpr_marque, qrpr_modele, qrpr_numserie, qrpr_codetechnique, "
			+ "qrpr_qrcode) VALUES (:designation, :marque, :modele, :numserie, :codetechnique, :qrcode)";
	
	public static final String QR_PRODUCTS = "SELECT qrpr_id, qrpr_designation, qrpr_marque, qrpr_modele, qrpr_numserie, qrpr_codetechnique, qrpr_qrcode "
			+ "FROM qr_produit ORDER BY qrpr_id ASC LIMIT :begin OFFSET :end";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameter;

	/* (non-Javadoc)
	 * @see com.simba.dao.ProductDao#findAll()
	 */
	@Override
	public List<Product> findAll() {
		return namedParameter.query(QR_ALL, new ProductRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.simba.dao.ProductDao#saveOrUpdate(com.simba.model.Product)
	 */
	@Override
	public int save(Product product) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("designation", product.getDesignation());
		params.put("marque", product.getMarque());
		params.put("modele", product.getModele());
		params.put("numserie", product.getNumeroSerie());
		params.put("codetechnique", product.getCodeTechnique());
		params.put("qrcode", product.getQrcode());
		
		return 0;
	}
	
	class ProductRowMapper implements RowMapper<Product> {

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("qrpr_id"));
			product.setDesignation(rs.getString("qrpr_designation"));
			product.setMarque(rs.getString("qrpr_marque"));
			product.setModele(rs.getString("qrpr_modele"));
			product.setNumeroSerie(rs.getString("qrpr_numserie"));
			product.setCodeTechnique(rs.getString("qrpr_codetechnique"));
			product.setQrcode(rs.getString("qrpr_qrcode"));
			return product;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.simba.dao.ProductDao#saveList(java.util.List)
	 */
	@Override
	public int saveList(List<Product> products) {
		List<Integer> countList = new ArrayList<>();
		for (Product product: products) {
			HashMap<String, Object> params = new HashMap<>();
			params.put("designation", product.getDesignation());
			params.put("marque", product.getMarque());
			params.put("modele", product.getModele());
			params.put("numserie", product.getNumeroSerie());
			params.put("codetechnique", product.getCodeTechnique());
			params.put("qrcode", product.getQrcode());
			int count = namedParameter.update(QR_INSERT, params);
			countList.add(count);
		}
		System.out.println("List Produits => " +products.size()+ "Count List => " +countList.size());
		return countList.size();
	}

	@Override
	public List<Product> getProducts(int begin, int end) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("begin", begin);
		params.put("end", end);
		return namedParameter.query(QR_PRODUCTS, params, new ProductRowMapper());
	}

}
