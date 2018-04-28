/**
 * 
 */
package com.simba.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.zxing.WriterException;
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
	
	void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException;
	
	void generateQRCode(final String path, int begin, int end);
	
	List<Product> getProducts(int begin, int end);
}
