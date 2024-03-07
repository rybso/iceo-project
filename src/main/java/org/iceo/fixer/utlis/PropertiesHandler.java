package org.iceo.fixer.utlis;

import java.util.ResourceBundle;

public class PropertiesHandler {

    private static final String BASE_URL = "base.url";

    private PropertiesHandler() {
        throw new IllegalStateException("Properties class");
    }

    private static String getRequiredData(final String key) {
        return ResourceBundle.getBundle(switch (System.getProperty("env") != null ? System.getProperty("env") : "test") {
            case "test":
                yield "test";
            case "stage":
                yield  "stage";
            default:
                throw new IllegalStateException("Unknown env value: " + System.getProperty("env"));
        }).getString(key);
    }

    public static String getBaseUrl() {
        return getRequiredData(BASE_URL);
    }
}
