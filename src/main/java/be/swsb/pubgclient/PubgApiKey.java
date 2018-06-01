package be.swsb.pubgclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class PubgApiKey {

    private static String pubgApiKey;

    public static String getPubgApiKey() {
        if (pubgApiKey == null) {
            pubgApiKey = readPubgApiKeyFromKeyFile();
        }
        return pubgApiKey;
    }

    public static void setPubgApiKey(String pubgApiKey) {
        PubgApiKey.pubgApiKey = pubgApiKey;
    }

    @SuppressWarnings("ThrowFromFinallyBlock")
    private static String readPubgApiKeyFromKeyFile() {
        Properties prop = new Properties();
        InputStream input = null;
        String result = "";
        try {
            input = new FileInputStream(Paths.get(System.getProperty("user.home"), ".key", "pubgapi.key").toString());
            prop.load(input);
            result = prop.getProperty("development");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}