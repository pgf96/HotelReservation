package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Override
    public String[] getWelcomeMessages() {
        String[] welcomeMessages = new String[2];

        Future<?> future1 = executor.submit(() -> {
            ResourceBundle messages = ResourceBundle.getBundle("welcome", Locale.ENGLISH);
            System.out.println("current thread for en: " + Thread.currentThread().getName());
            welcomeMessages[0] = messages.getString("welcome");
        });

        Future<?> future2 = executor.submit(() -> {
            ResourceBundle messages = ResourceBundle.getBundle("welcome", Locale.CANADA_FRENCH);
            System.out.println("current thread for fr: " + Thread.currentThread().getName());
            welcomeMessages[1] = messages.getString("welcome");
        });

        try {
            future1.get();
            future2.get();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return welcomeMessages;
    }
}
