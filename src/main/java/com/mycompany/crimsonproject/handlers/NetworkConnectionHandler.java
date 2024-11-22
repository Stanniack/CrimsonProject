package com.mycompany.crimsonproject.handlers;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.interfaces.NetworkConnectionVerifier;
import com.mycompany.crimsonproject.utils.CalendarUtils;
import com.mycompany.crimsonproject.utils.HostTools;

/**
 *
 * @author Devmachine
 */
public class NetworkConnectionHandler implements NetworkConnectionVerifier {

    @Override
    public boolean networkVerifier() {
        HostTools host = new HostTools();

        if (!host.checkHostConnection()) {
            CalendarUtils cu = new CalendarUtils();
            TextLogs textLogs = new TextLogs();
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\lostconnection.txt";
            String message = "Lost connection at " + cu.getDate();
            textLogs.createLogMessage(path, message);
            return false;
        }
        return true;
    }
}
