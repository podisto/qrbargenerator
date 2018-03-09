/**
 * 
 */
package com.simba.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author podisto
 *
 */
@ConfigurationProperties("storage")
@Getter @Setter
public class StorageProperties {
	private String location = "upload-dir";
}
