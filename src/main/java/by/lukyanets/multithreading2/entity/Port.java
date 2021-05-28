package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Port {
    private static final Port INSTANCE = new Port();
    private final Semaphore piers;
    private final Semaphore portCapacity;
    private final Semaphore availableContainers;

    private Port() {
        DataReader reader = new DataReader();
        List<String> data = reader.reader(Util.findAbsolutePath("data.txt"));
        String s = data.get(0);
        String[] split = s.split(" ");
        piers = new Semaphore(Integer.parseInt(split[0]));
        portCapacity = new Semaphore(Integer.parseInt(split[1]));
        availableContainers = new Semaphore(Integer.parseInt(split[2]));
    }

    public static Port getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Port{" +
                "piers=" + piers +
                ", portCapacity=" + portCapacity +
                ", availableContainers=" + availableContainers +
                '}';
    }
}
