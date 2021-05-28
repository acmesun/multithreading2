package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.parser.DataParser;
import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Port {
    private static final Port INSTANCE = new Port();
    private final Semaphore piers;
    private final Semaphore portCapacity;
    private final Semaphore availableContainers;


    private DataReader reader = new DataReader();
    private DataParser parser = new DataParser();

    private Port() {
        List<String> reader = this.reader.reader(Util.findAbsolutePath("data.txt"));
        String s = reader.get(0);
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
