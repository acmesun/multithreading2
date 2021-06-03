package by.lukyanets.multithreading2.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class PortContainerController extends TimerTask {
    private final Logger logger = LogManager.getLogger(PortContainerController.class);
    private final Port port = Port.getInstance();

    @Override
    public void run() {
        int minThreshold = port.getAvailableContainers().availablePermits() / 4;
        int maxThreshold = minThreshold * 3;
        int portCapacity = port.getPortCapacity().availablePermits();
        int availableContainers = port.getAvailableContainers().availablePermits();

        if (availableContainers <= minThreshold && availableContainers + minThreshold <= portCapacity) {
            try {
                port.unload(minThreshold);
                logger.info("{} containers were unloaded into the port.", minThreshold);
            } catch (InterruptedException e) {
                logger.error("Failed to unload {} containers", minThreshold);
            }
        } else if (availableContainers >= maxThreshold) {
            try {
                port.load(minThreshold);
                logger.info("{} containers were loaded into the port", minThreshold);
            } catch (InterruptedException e) {
                logger.error("Failed to load {} containers", minThreshold);
            }
        }
    }
}
