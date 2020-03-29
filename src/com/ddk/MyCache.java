package com.ddk;

import java.util.*;

import static java.lang.Thread.sleep;

class Data {
    String key;
    String value;
    Long creationTime; // in ms
    Integer tol; // In ms

    public Data(String key, String value) {
        add(key, value);
    }

    synchronized public void add(String key, String value) {
        this.key = key;
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.tol = 60000;
    }

    synchronized public void add(String key, String value, Integer tol) {
        add(key, value);
        this.tol = tol;
    }

    synchronized public void setTol(Integer tol) {
        this.tol = tol;
    }

    synchronized public String getKey() {
        return this.key;
    }

    synchronized public String getValue() {
        return this.value;
    }

    synchronized public boolean isExpired() {
        return (System.currentTimeMillis() > (this.creationTime + this.tol));
    }

    @Override
    synchronized public String toString() {
        StringBuilder sb = new StringBuilder("key: " + key);
        sb.append(", ").append("value: " + value);
        sb.append(", ").append("creationTime: " + creationTime);
        sb.append(", ").append("tol: " + tol);
        sb.append(", ").append("isExpired: " + isExpired());
        return sb.toString();
    }
}

class Cache {
    Map<String, Data> cacheData;

    public Cache() {
        cacheData = new Hashtable<>();
    }

    synchronized public void add(String key, String value) {
        cacheData.put(key, new Data(key, value));
    }

    synchronized public boolean setTol(String key, Integer tol) {
        boolean isSuccess = false;
        if ( cacheData.containsKey(key) ) {
            cacheData.get(key).setTol(tol);
            isSuccess = true;
        }
        return isSuccess;
    }

    synchronized public Data getData(String key) {
        return cacheData.get(key);
    }

    synchronized public String getValue(String key) {
        Data d = getData(key);
        if ( d != null ) {
            if (d.isExpired()) {
                cacheData.remove(key);
                return null;
            } else
                return d.getValue();
        }
        return null;
    }

    synchronized public void mySleep(Integer timeToSleep) {
        try {
            sleep(timeToSleep);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void deleteIfExpired(String key) {
        synchronized (cacheData) {
            if (cacheData.containsKey(key)) {
                if (cacheData.get(key).isExpired()) {
                    System.out.println("Key " + key + " is expired ... deleting it");
                    cacheData.remove(key);
                } else {
                    System.out.println("Key " + key + " is not expired");
                }
            } else {
                System.out.println("Key " + key + " not found");
            }
        }
        Thread.yield();
    }
}

class CleanUp extends Thread {
    Cache cache;

    public CleanUp(Cache cache) {
        this.cache = cache;
    }

    public void run() {
        while ( true ) {
            Set<String> keys = cache.cacheData.keySet();
            for ( String key : keys ) {
                cache.deleteIfExpired(key);
            }
            cache.mySleep(1000);
        }
    }
}

public class MyCache {
    public static void main(String[] args) {
        Cache cache = new Cache();
        CleanUp cleanUp = new CleanUp(cache);
        cleanUp.setDaemon(true);
        cleanUp.start();

        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String key = "" + 'a' + i;
            keys.add(key);
            cache.add(key, "" + (i + 1));
            cache.mySleep(100);
        }

        for ( String key : keys ) {
            System.out.println(cache.getData(key));
            cache.setTol(key, 500);
        }

        cache.mySleep(1000);

        for ( String key : keys ) {
            System.out.println(cache.getData(key));
        }
    }
}