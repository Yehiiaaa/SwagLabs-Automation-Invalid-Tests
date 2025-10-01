package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.LinkedHashMap;

public class JSONFileManager {
    LinkedHashMap<String, Object> data;

    public JSONFileManager(String filePath) {
        try {
            data = new Gson().fromJson(new FileReader(filePath),
                    new TypeToken<LinkedHashMap<String, Object>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getValue(String key) {
        return data.get(key);
    }
}
