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
     *
     * @param clicker clicker's name in .json file to be read
     * @return a clicker type Map key:valor
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
     * @param rect rectangle button/clicker to be saved with status not
     * deprecated
     * @param clicker name of object in .json file
     * @return true if clicked has been saved or false if not
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
     * @param clicker name to convert to Rectangle
     * @return a Rectangle with x, y, w, h info
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
