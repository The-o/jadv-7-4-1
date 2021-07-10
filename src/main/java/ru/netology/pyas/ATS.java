package ru.netology.pyas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS {

    private static final int NUM_CALLS = 60;
    private static final int CALLS_TIMEOUT = 1000;

    private volatile boolean isTerminated = false;
    private Queue<String> callsQueue = new ConcurrentLinkedQueue<>();

    public void run(){
        for (int i = 0; i < NUM_CALLS; ++i) {
            String callName = "звонок " + (i+1);
            System.out.println("АТС: пришёл " + callName);
            callsQueue.add(callName);
            try {
                Thread.sleep(CALLS_TIMEOUT);
            } catch (InterruptedException e) {
                return;
            }
        }
        while(!callsQueue.isEmpty());
        isTerminated = true;
    }

    public String getNextCall() {
        return callsQueue.poll();
    }

    public boolean isTerminated() {
        return isTerminated;
    }
}
