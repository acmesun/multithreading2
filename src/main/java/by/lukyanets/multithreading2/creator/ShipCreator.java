package by.lukyanets.multithreading2.creator;

import by.lukyanets.multithreading2.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShipCreator {

    private final Logger logger = LogManager.getLogger(ShipCreator.class);

    public List<Ship> create(List<String> data) {
        List<Ship> ships = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            String s = data.get(i);
            String[] split = s.split(" ");
            ships.add(new Ship(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        logger.info("Ships from data {} created.", data);
        return ships;
    }
}
