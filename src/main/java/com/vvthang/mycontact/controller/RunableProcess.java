package com.vvthang.mycontact.controller;

public class RunableProcess implements Runnable{

    private Thread t;
    private String threadName;

    RunableProcess(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        while (!t.isInterrupted()) {           
            System.out.println("Thread: " + threadName);

        }

        System.out.println("Stopped Running....");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }


}
