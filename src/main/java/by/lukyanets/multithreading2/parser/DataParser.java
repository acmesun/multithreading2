package by.lukyanets.multithreading2.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataParser {
    public List<List<String>> parse(List<String> data) {
        List<List<String>> dataForObjects = new ArrayList<>();
        for (String string : data) {
            String[] splitData = string.split("\n");
            List<String> toData = new ArrayList<>();
            toData.add(Arrays.toString(splitData));
            dataForObjects.add(toData);
        }
        return dataForObjects;
    }
}
