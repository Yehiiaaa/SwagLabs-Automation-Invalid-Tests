package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    Properties properties;
    public ConfigLoader(String filePath){
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getValue (String key)
    {
        return properties.getProperty(key);
    }
}
