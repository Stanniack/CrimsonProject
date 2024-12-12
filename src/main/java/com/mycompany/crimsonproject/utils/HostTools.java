package com.mycompany.crimsonproject.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Devmachine
 */
public class HostTools {

    /**
     * Checks if the system can establish a connection to the internet by
     * attempting to reach "www.google.com".
     *
     * @return {@code true} if the system can connect to the host, otherwise
     * {@code false}.
     */
    public boolean checkHostConnection() {
        try {
            InetAddress.getByName("www.google.com");
        } catch (UnknownHostException ex) {
            return false;
        }
        return true;
    }
}
