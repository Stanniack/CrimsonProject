
package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.robot.DragScreen;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanniack
 */
public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("P1:CS - i: 130", 10);
        
        var item = (Map.Entry<String, Integer>) hm.entrySet();
        
        System.out.println(item.getKey().contains("P1:CS - i: 130"));
        
    }
}
