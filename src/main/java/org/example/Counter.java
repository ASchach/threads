package org.example;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class Counter {

//variables
    private Output output;

    private int max = 200_000;

    private int threadLimit = 4;

//constructors
    public Counter() {
    }

    public Counter(int max) {
        this.max = max;
    }

//methods
    public void setOutput(Output output) {
        this.output = output;
    }

    public int getValue() {
        return value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    private int value;

    public void startIncrement()
    {
        List<Thread> threadList = new ArrayList<>();
        for (int i=0; i<threadLimit; i++)
        {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < max / threadLimit; i++){
                        increment();
                    }
                    if(output != null) {
                        output.append("Thread " + finalI + " finished counting");
                    }
                }
            });
            threadList.add(thread);
        }
        for(Thread thread :threadList)
            thread.start();

        for(Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private synchronized void increment(){
        value++;
    }
    public static void main(String[] args) {
        Counter counter = new Counter(500_000);
        counter.setOutput(new Output() {
            @Override
            public void append(String message) {
                System.out.println(message);
            }
        });
        counter.startIncrement();

    }
}
