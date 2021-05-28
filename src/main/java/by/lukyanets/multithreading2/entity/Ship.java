package by.lukyanets.multithreading2.entity;

public class Ship extends Thread {
    private Integer onBoard;
    private Integer size;

    public Ship(Integer onBoard, Integer size) {
        this.onBoard = onBoard;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "onBoard=" + onBoard +
                ", size=" + size +
                '}';
    }
}
