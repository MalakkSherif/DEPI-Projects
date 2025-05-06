package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonReader {

    public static Map<String, String> readDataFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
