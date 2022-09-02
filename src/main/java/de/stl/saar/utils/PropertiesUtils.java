package de.stl.saar.utils;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class PropertiesUtils {
	final File configFile = new File("abschreibungen.properties");

	public Properties properties = new Properties();

	public void loadProperties() throws IOException {
		if (!configFile.exists()) {
			configFile.createNewFile();
			try (FileWriter writer = new FileWriter(configFile)) {
				properties.setProperty("theme", "dark");
				properties.store(writer, "Write Properties");
				return;
			}
		}
		try (FileReader writer = new FileReader(configFile)) {
			properties.load(writer);
		}
	}

	public String getTheme() {
		return properties.getProperty("theme");
	}
}
