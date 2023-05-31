package com.example.beans.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

public class JournalLogger implements AppLogger{
    private Logger logger = LoggerFactory.getLogger(JournalLogger.class);
    @Override
    public void getLogger() {
        logger.info("journal logger was called");
    }
}
