package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.exception.ThreadException;

public class Ship extends Thread {
    private final Port port = Port.getINSTANCE();

    private Integer onBoard;
    private Integer size;

    public Ship(Integer onBoard, Integer size) {
        this.onBoard = onBoard;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            port.arrive();
            if (this.getOnBoard() > 0) {
                port.unload(this.getOnBoard());
            } else {
                port.load(this.getSize());
            }
        } catch (ThreadException | InterruptedException e) {
            e.printStackTrace();
        } finally {
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
