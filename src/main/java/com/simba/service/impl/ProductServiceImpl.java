/**
 * 
 */
package com.simba.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simba.dao.ProductDao;
import com.simba.model.Product;
import com.simba.service.ProductService;

/**
 * @author podisto
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.ProductService#findAll()
	 */
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.ProductService#save(com.simba.model.Product)
	 */
	@Override
	public int save(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.ProductService#saveList(java.util.List)
	 */
	@Override
	public int saveList(List<Product> products) {
		// TODO Auto-generated method stub
		return productDao.saveList(products);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.ProductService#loadExcelData(java.lang.String)
	 */
	@Override
	public HashMap<String, Object> loadExcelData(String filename) {
		// TODO Auto-generated method stub
		List<Product> listProducts = new ArrayList<>();
		HashMap<String, Object> resultMap = new HashMap<>();
		try {
			FileInputStream excelFile = new FileInputStream(filename);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			resultMap.put("total", sheet.getLastRowNum() + 1); // compte a partir de l'index 0
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Product product = new Product();
				row = sheet.getRow(i);
				if (row.getRowNum() == 0)
					continue; // skip first row, as it contains column names.
				
				String batiment = (row.getCell(0) != null && row.getCell(0).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(0)) : "");
				String bureau = (row.getCell(1) != null && row.getCell(1).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(1)) : "");
				String ordre = (row.getCell(2) != null && row.getCell(2).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(2)) : "");
				String designation = (row.getCell(3) != null && row.getCell(3).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(3)) : "");
				String marque = (row.getCell(4) != null && row.getCell(4).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(4)) : "");
				String modele = (row.getCell(5) != null && row.getCell(5).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(5)) : "");
				String numserie = (row.getCell(6) != null && row.getCell(6).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(6)) : "");
				String famille = (row.getCell(7) != null && row.getCell(7).getCellTypeEnum() != CellType.BLANK ? new DataFormatter().formatCellValue(row.getCell(7)) : "");
				product.setBatiment(batiment);
				product.setBureau(bureau);
				product.setOrdre(ordre);
				product.setDesignation(designation);
				product.setMarque(marque);
				product.setModele(modele);
				product.setNumeroSerie(numserie);
				product.setFamille(famille);
				// code technique => BATIMENT/BUREAU/FAMILLE/ORDRE
				product.setCodeTechnique(product.getBatiment()+"/"+product.getBureau()+"/"+product.getFamille()+"/"+product.getOrdre());
				product.setQrcode(product.getDesignation()+" " +product.getMarque()+ " " +product.getModele()+ " " +product.getNumeroSerie());
				listProducts.add(product);
			}
			resultMap.put("products", listProducts);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Une erreur s'est produite " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}

}
