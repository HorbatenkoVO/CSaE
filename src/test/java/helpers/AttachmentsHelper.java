package helpers;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.google.gson.JsonParser.parseString;

public class AttachmentsHelper {
    @Attachment(value = "{attachName}", type = "text/plain", fileExtension = ".txt")
    public static String attachAsText(String attachName, String message) {
        System.out.println(message);
        return message;
    }

    @Attachment(value = "{attachName}", type = "text/json", fileExtension = ".json")
    public static String attachAsJson(String attachName, String message) {
        JsonElement jsonElement = parseString(message);
        message = new GsonBuilder().setPrettyPrinting().create().toJson(jsonElement);
        return message;
    }

    @Attachment(value = "{attachName}", type = "text/json", fileExtension = ".json")
    public static byte[] attachAsJson(String attachName, ByteArrayOutputStream stream) {
        byte[] array = stream.toByteArray();
        stream.reset();
        return array;
    }

    @Attachment(value = "{attachName}", type = "text/xml", fileExtension = ".xml")
    public static String attachAsXml(String attachName, String message) {
        return message;
    }

    @Step("Данные об окружении добавлены в отчёт")
    public static void setEnvironmentProperties(String text) {
        String filePath = "target/allure-results/environment.properties";
        try (FileOutputStream fos = new FileOutputStream(filePath, true)) {
            text = text + "\n";
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ignored) {
        }
    }
}
