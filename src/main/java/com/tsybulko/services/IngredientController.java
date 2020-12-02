package com.tsybulko.services;

import com.tsybulko.services.factory.impl.ServiceFactoryImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class IngredientController implements Runnable {
    private final static long MILLIS_IN_DAY = 1000L * 60 * 60 * 24;
    private static final Logger logger = Logger.getLogger(IngredientController.class);

    @Override
    public void run() {
        try {
            while (true) {
                ServiceFactoryImpl.getServiceFactoryInstance().getIngredientService().checkExpirationDate();
                Thread.currentThread().sleep(MILLIS_IN_DAY);
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
            e.printStackTrace();
        }
    }
}
