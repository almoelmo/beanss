package com.example.beans.conditions;

import org.springframework.stereotype.Component;

public class ConsoleLogger implements AppLogger{
    @Override
    public void getLogger() {
        System.out.println("Logger from console");
    }
}
