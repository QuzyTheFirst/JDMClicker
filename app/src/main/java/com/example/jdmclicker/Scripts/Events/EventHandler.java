package com.example.jdmclicker.Scripts.Events;

import java.util.ArrayList;

public class EventHandler<TEventArgs> implements IEvent<TEventArgs>{
    private ArrayList<IEvent<TEventArgs>> eventDelegateArray = new ArrayList<>();

    public void AddCallback(IEvent<TEventArgs> methodReference){
        eventDelegateArray.add(methodReference);
    }

    public void RemoveCallback(IEvent<TEventArgs> methodReference){
        eventDelegateArray.remove(methodReference);
    }

    public void invoke(Object source, TEventArgs eventArgs){
        if(eventDelegateArray.size() > 0)
            eventDelegateArray.forEach(p -> p.invoke(source, eventArgs));
    }

    public void close(){
        if(eventDelegateArray.size() > 0){
            eventDelegateArray.clear();
        }
    }
}
