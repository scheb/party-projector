package de.christianscheb.partyprojector.model;

import java.util.Observable;
import java.util.concurrent.Semaphore;

public class StreamModel extends Observable {

    private Semaphore lock = new Semaphore(1);
    private String streamIp;

    public String getStreamIp() {
        return streamIp;
    }

    public void startStream(String ip) throws InterruptedException {
        lock.acquire();
        streamIp = ip;
        setChanged();
        notifyObservers();
    }

    public void releaseStream() {
        lock.release();
        streamIp = null;
    }
}
