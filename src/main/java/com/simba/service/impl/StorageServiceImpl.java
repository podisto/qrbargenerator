/**
 * 
 */
package com.simba.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.simba.exception.StorageException;
import com.simba.exception.StorageFileNotFoundException;
import com.simba.service.StorageService;
import com.simba.utils.StorageProperties;

/**
 * @author podisto
 *
 */
@Service
public class StorageServiceImpl implements StorageService {

	private static final String TmpDir = System.getProperty("java.io.tmpdir");
	private final Path rootLocation;

	@Autowired
	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.StorageService#init()
	 */
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.simba.service.StorageService#store(org.springframework.web.multipart.
	 * MultipartFile)
	 */
	@Override
	public File store(MultipartFile file) {
		try {
			Random random = new Random();
			return this.copyInputStreamToFile(TmpDir + "/" + random.nextInt() + ".xlsx", file.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException("Fail !");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.simba.service.StorageService#storeFile(org.springframework.web.multipart.
	 * MultipartFile)
	 */
	@Override
	public void storeFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) { // This is a security check throw
				new StorageException("Cannot store file with relative path outside current directory " + filename);
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.StorageService#load(java.lang.String)
	 */
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.StorageService#loadAsResource(java.lang.String)
	 */
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simba.service.StorageService#deleteAll()
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	private File copyInputStreamToFile(String fileName, InputStream in) {
		try {
			File file = new File(fileName);
			// System.out.println("file => " + file.toString().replaceAll("-", ""));
			OutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.flush();
			out.close();

			return file;
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, " Exception caught " + e.getMessage(),
					e);
			System.out.println(e.getMessage());
		}
		return null;
	}

}
