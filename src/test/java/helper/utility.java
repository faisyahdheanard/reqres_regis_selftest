package helper;

import java.io.File;

public class utility {
    public static File getJSONSchema(String JSONFile) {
        return new File("src/test/java/helper/JSONSchemaData/" + JSONFile);
    }
}
