package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static String getPropertyValue(String key) throws IOException {

        FileReader reader = new FileReader("src//main//Resources//Environment.properties");
        Properties prop = new Properties();
        prop.load(reader);
        return prop.getProperty(key);
    }
}
