package ru.netology.pyas;

import java.util.Random;

public class Operator {

    private static final int MIN_CALL_PROCESSING_TIME = 3000;
    private static final int MAX_CALL_PROCESSING_TIME = 4000;
    private String name;
    private ATS ats;

    public Operator(String name, ATS ats) {
        this.name = name;
        this.ats = ats;
    }

    public void run() {
        Random rng = new Random();
        while(!ats.isTerminated()) {
            String call = ats.getNextCall();
            if(call == null) {
                continue;
            }
            System.out.println(name + ": взял " + call);
            int callProcessingTime = MIN_CALL_PROCESSING_TIME + rng.nextInt(MAX_CALL_PROCESSING_TIME - MIN_CALL_PROCESSING_TIME);
            try {
                Thread.sleep(callProcessingTime);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(name + ": завершил " + call);
        }
    }

}
