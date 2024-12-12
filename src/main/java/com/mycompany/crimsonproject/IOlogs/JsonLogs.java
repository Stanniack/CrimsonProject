package com.mycompany.crimsonproject.IOlogs;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class JsonLogs {

    private ObjectMapper objectMapper;
    private String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\pointerRectangle.json";

    public JsonLogs() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Reads a specific clicker data from a JSON file and returns it as a map.
     *
     * @param clicker the key identifying the clicker in the JSON file.
     * @return a map containing the clicker data, or {@code null} if an error
     * occurs.
     */
    public Map<String, Object> readClicker(String clicker) {
        try {
            Map<String, Map<String, Object>> data = objectMapper.readValue(new File(path), Map.class);
            Map<String, Object> pointerClicker = (Map<String, Object>) data.get(clicker);

            return pointerClicker;
        } catch (IOException ex) {
            Logger.getLogger(JsonLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Saves the dimensions and state of a clicker to a JSON file.
     *
     * @param rect the rectangle containing the dimensions of the clicker.
     * @param clicker the key identifying the clicker in the JSON file.
     * @return {@code true} if the data is saved successfully, otherwise
     * {@code false}.
     */
    public boolean saveClicker(Rectangle rect, String clicker) {
        try {
            Map<String, Map<String, Object>> json = objectMapper.readValue(new File(path), Map.class);
            Map<String, Object> pointerClicker = (Map<String, Object>) json.get(clicker);
            pointerClicker.put("x", rect.x);
            pointerClicker.put("y", rect.y);
            pointerClicker.put("w", rect.width);
            pointerClicker.put("h", rect.height);
            pointerClicker.put("deprecated", false);
            objectMapper.writeValue(new File(path), json);

            return true;
        } catch (IOException ex) {
            Logger.getLogger(JsonLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Retrieves the dimensions of a clicker from a JSON file as a
     * {@link Rectangle}.
     *
     * @param clicker the key identifying the clicker in the JSON file.
     * @return a {@link Rectangle} representing the clicker's dimensions, or
     * {@code null} if an error occurs.
     */
    public Rectangle getClicker(String clicker) {
        try {
            Map<String, Map<String, Object>> json = objectMapper.readValue(new File(path), Map.class);
            Map<String, Object> pointerClicker = (Map<String, Object>) json.get(clicker);
            Rectangle rect = new Rectangle(
                    (int) pointerClicker.get("x"),
                    (int) pointerClicker.get("y"),
                    (int) pointerClicker.get("w"),
                    (int) pointerClicker.get("h"));

            return rect;
        } catch (IOException ex) {
            Logger.getLogger(JsonLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
