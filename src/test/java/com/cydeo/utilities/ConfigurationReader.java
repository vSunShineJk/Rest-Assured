package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties = new Properties();
    // Object to Update file
    private static Properties updateProperties = new Properties();

    // we want to open properties file and load to properties object ONLY ONCE before reading
    // having static block because static runs first
     static {
        try {
            FileInputStream file = new FileInputStream("configuration.properties");
            properties.load(file);

            // close the file after loading, otherwise it will take from computer memory like scanner
            file.close();

            // the code below to another independent update.properties file
            FileInputStream updateFile = new FileInputStream("update.properties");
            updateProperties.load(updateFile);
            updateFile.close();
        } catch (IOException e){

        }
    }

    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }

    // Getter for Update file
    public static String getUpdateProperty(String keyword) {
         return updateProperties.getProperty(keyword);
    }

}
