package by.lukyanets.multithreading2._main;

import by.lukyanets.multithreading2.creator.ShipCreator;
import by.lukyanets.multithreading2.entity.Port;
import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;

public class Main {
    public static void main(String[] args) {
        Port port = Port.getINSTANCE();
        System.out.println(port);
        var data = new DataReader().reader(Util.findAbsolutePath("data.txt"));
        var ships = new ShipCreator().create(data);
        ships.forEach(Thread::start);
    }
}

