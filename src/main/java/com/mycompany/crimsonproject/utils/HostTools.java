package com.mycompany.crimsonproject.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Devmachine
 */
public class HostTools {

    public boolean checkHostConnection() {
        try {
            InetAddress.getByName("www.google.com");
        } catch (UnknownHostException ex) {
            return false;
        }

        return true;
    }
}
