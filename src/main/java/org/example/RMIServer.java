package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        int num=20;
        String random_str;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        long startTime = System.nanoTime();
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service

            for (int i=1;i<num;i++) {
                random_str=service.givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();

                service.addElem(random_str);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);

            String result = service.returnRes();
            long endTime = System.nanoTime();
            System.out.println("Result: "+result+" Time it took to complete the task: "+(endTime-startTime)/1000000000+" seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}