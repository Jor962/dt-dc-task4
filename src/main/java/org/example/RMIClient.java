package org.example;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;



public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            String str = service.pollElem();
            while (service.isWorking()) {
                if (service.isEmpty()) {
                    System.out.println("Result: " + service.getResult());
                    service.setWorking(false);
                    return;
                }

                System.out.println("Received: " + str);
                service.addResult(str);
                System.out.println("Sent: " + str);
                Thread.sleep(1000);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
