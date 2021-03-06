package com.koabs.design.observer;

import java.util.ArrayList;

/**
 * Created by kevin1 on 7/6/15.
 */
public class Subject {
    // Fields
    private ArrayList<Observer> observers = new ArrayList();

    // Methods
    public void Attach( Observer observer ) {
        observers.add(observer);
    }

    public void Detach( Observer observer ) {
        observers.remove(observer);
    }

    public void Notify() {
        for( Observer o : observers ){
            o.Update();
        }
    }
}
