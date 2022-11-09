package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a service which will be accessible remotely
 */
public interface Service extends Remote {
    String pollElem() throws RemoteException;
    void addElem(String str) throws RemoteException;

    String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() throws RemoteException;

    public void addResult(String str) throws RemoteException;
    public String getResult() throws RemoteException;
    public boolean isEmpty() throws RemoteException;
    public void setWorking(boolean isWorking) throws RemoteException;
    public boolean isWorking() throws RemoteException;
    public String returnRes() throws RemoteException, InterruptedException;
}
