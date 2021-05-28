package by.lukyanets.multithreading2._main;

import by.lukyanets.multithreading2.exception.ThreadException;
import by.lukyanets.multithreading2.parser.DataParser;
import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ThreadException {
        DataReader reader = new DataReader();
        DataParser parser = new DataParser();
        List<String> data = reader.reader(Util.findAbsolutePath("data.txt"));
        System.out.println(parser.parse(data));
    }
}
