/**
 * 
 */
package com.simba.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.simba.model.JsonResponse;
import com.simba.model.Product;
import com.simba.service.ProductService;
import com.simba.service.StorageService;

/**
 * @author podisto
 *
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private ProductService productSrv;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/products/upload", method = RequestMethod.POST)
	public @ResponseBody JsonResponse upload(@RequestParam("file") MultipartFile file) {
		try {
			File up = storageService.store(file);
			HashMap<String, Object> map = productSrv.loadExcelData(up.getAbsolutePath());
			List<Product> listProducts = (List<Product>) map.get("products");
			System.out.println("liste" +listProducts.size());
			int total = (Integer) map.get("total");
			return new JsonResponse(200, listProducts.size() + " produits importés avec succès sur un total de " + total,
					listProducts);
		} catch (Exception e) {
			return new JsonResponse(HttpStatus.EXPECTATION_FAILED.value(),
					"Impossible d'importer " + file.getOriginalFilename() + " Erreur " + e.getMessage(), null);
		}

	}
}