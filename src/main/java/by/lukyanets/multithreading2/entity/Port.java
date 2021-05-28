package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.exception.ThreadException;
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

    private Port(){
        List<String> reader = this.reader.reader(Util.findAbsolutePath("data.txt"));
        List<List<String>> lists = parser.parse(reader);
        List<String> portData = lists.get(0);
        String splitPortData = portData.get(0);
        String[] split = splitPortData.split(" ");
        piers = new Semaphore(Integer.parseInt(split[0]));
        portCapacity = new Semaphore(Integer.parseInt(split[1]));
        availableContainers = new Semaphore(Integer.parseInt(split[2]));
    }

    public static Port getINSTANCE() {
        return INSTANCE;
    }
}
