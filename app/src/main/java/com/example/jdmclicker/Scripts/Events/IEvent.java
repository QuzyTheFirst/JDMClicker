package com.example.jdmclicker.Scripts.Events;

@FunctionalInterface
public interface IEvent<TEventArgs extends Object>{
    void invoke(Object source, TEventArgs eventArgs);
}
