package com.example;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.h2.console")
public class H2ConsoleProperties {
 
	/**
	 * Path at which the console will be available.
	 */
	@NotNull
	@Pattern(regexp = "/[^?#]*", message = "Path must start with /")
	private String path = "/h2-console";
 
	/**
	 * Enable the console.
	 */
	private boolean enabled = false;
 
	public String getPath() {
		return this.path;
	}
 
	public void setPath(String path) {
		this.path = path;
	}
 
	public boolean getEnabled() {
		return this.enabled;
	}
 
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
 
}