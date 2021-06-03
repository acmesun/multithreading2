package by.lukyanets.multithreading2._main;

import by.lukyanets.multithreading2.creator.ShipCreator;
import by.lukyanets.multithreading2.entity.Port;
import by.lukyanets.multithreading2.entity.Worker;
import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        Port port = Port.getINSTANCE();
        Timer timer = new Timer();
        System.out.println(port);
        var data = new DataReader().reader(Util.findAbsolutePath("data.txt"));
        var ships = new ShipCreator().create(data);
        ExecutorService service = Executors.newFixedThreadPool(ships.size());
        timer.schedule(new Worker(), 1000, 5000);
        service.submit(() -> ships.forEach(Thread::start));
        ships.forEach(ship -> {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timer.cancel();
        AtomicBoolean needWorker = new AtomicBoolean(true);
        ships.forEach(ship -> {
            if (!ship.isAlive()) {
                needWorker.set(false);
            }
        });
        if (!needWorker.get()) {
            timer.cancel();
        }

    }
}

