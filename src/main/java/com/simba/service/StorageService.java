/**
 * 
 */
package com.simba.service;

import java.io.File;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author podisto
 *
 */
public interface StorageService {

	void init();

	File store(MultipartFile file);

	void storeFile(MultipartFile file);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
}
