package org.example;

import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<String> queue;
    private String result;

    private boolean isWorking = true;

    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public String pollElem() throws RemoteException {
        return this.queue.poll();
    }

    @Override
    public void addElem(String str) throws RemoteException {
        this.queue.add(str);
        System.out.println("Added: " + str);
    }
    public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() throws RemoteException {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
    @Override
    public void addResult(String str){
        this.result+=str;
    }

    @Override
    public String getResult(){
        return this.result;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String returnRes() throws InterruptedException{
        while (!isEmpty()&&isWorking()){

        }
        Thread.sleep(1000);
        return getResult();
    }

    @Override
    public boolean isWorking(){
        return isWorking;
    }

    @Override
    public void setWorking(boolean isWorking) throws RemoteException {
        this.isWorking = isWorking;
    }
}
