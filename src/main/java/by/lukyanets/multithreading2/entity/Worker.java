package by.lukyanets.multithreading2.entity;

import java.util.TimerTask;

public class Worker extends TimerTask {
    Port port = Port.getINSTANCE();
    @Override
    public void run() {
        int minThreshold = port.getAvailableContainers().availablePermits() / 4;
        int maxThreshold = minThreshold * 3;
        int portCapacity = port.getPortCapacity().availablePermits();
        int availableContainers = port.getAvailableContainers().availablePermits();

        if (availableContainers <= minThreshold && availableContainers + minThreshold <= portCapacity) {
            try {
                port.unload(minThreshold);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (availableContainers >= maxThreshold) {
            try {
                port.load(minThreshold);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
