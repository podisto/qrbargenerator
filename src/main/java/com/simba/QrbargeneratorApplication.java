package com.simba;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.simba.model.Product;
import com.simba.service.impl.ProductServiceData;
import com.simba.utils.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan(basePackages = "com.simba")
@PropertySource("classpath:application.properties")
public class QrbargeneratorApplication {

	private static final String QR_CODE_IMAGE_PATH = "./codes/";

	public static void main(String[] args) {
		SpringApplication.run(QrbargeneratorApplication.class, args);
		List<Product> products = new ArrayList<>();
		ProductServiceData pService = new ProductServiceData();
		products = pService.init();
		System.out.println(products);
		try {
			for (Product item : products) {
				String marque = item.getMarque() == null ? "" : item.getMarque();
				String modele = item.getModele() == null ? "" : item.getModele();
				String numSerie = item.getNumeroSerie() == null ? "" : item.getNumeroSerie();
				String qrString = item.getDesignation() + " " + marque + " " + modele + " " + numSerie;
				generateQRCodeImage(qrString, 794, 265,
						QR_CODE_IMAGE_PATH + item.getCodeTechnique().replace("/", "_") + ".png");
			}
		} catch (WriterException e) {
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
	}

	private static void generateQRCodeImage(String text, int width, int height, String filePath)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}
}
