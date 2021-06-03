package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.exception.ThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship extends Thread {
    private static final Port port = Port.getINSTANCE();
    private final Logger logger = LogManager.getLogger(Ship.class);

    private Integer onBoard;
    private Integer size;

    public Ship(Integer onBoard, Integer size) {
        this.onBoard = onBoard;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            logger.info("Ship {} is trying to arrive. Ship has {} containers, max size is {}.", Thread.currentThread().getName(), this.getOnBoard(), this.getSize());
            port.arrive();
            if (this.getOnBoard() > 0) {
                port.unload(this.getOnBoard());
            } else {
                port.load(this.getSize());
            }
        } catch (ThreadException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            logger.info("Ship {} departed. Ship has {} containers.", Thread.currentThread().getName(), this.getOnBoard());
            port.depart();
        }
    }

    public Integer getOnBoard() {
        return onBoard;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "onBoard=" + onBoard +
                ", size=" + size +
                '}';
    }
}
