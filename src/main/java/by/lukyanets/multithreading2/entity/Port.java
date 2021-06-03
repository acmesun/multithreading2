package by.lukyanets.multithreading2.entity;

import by.lukyanets.multithreading2.exception.ThreadException;
import by.lukyanets.multithreading2.reader.DataReader;
import by.lukyanets.multithreading2.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Port {
    private static final Port instance = new Port();
    private final Logger logger = LogManager.getLogger(Port.class);
    private final Semaphore piers;
    private final Semaphore portCapacity;
    private final Semaphore availableContainers;

    public Semaphore getPortCapacity() {
        return portCapacity;
    }

    public Semaphore getAvailableContainers() {
        return availableContainers;
    }

    private Port() {
        DataReader reader = new DataReader();
        List<String> data = reader.reader(Util.findAbsolutePath("data.txt"));
        String s = data.get(0);
        String[] split = s.split(" ");
        piers = new Semaphore(Integer.parseInt(split[0]));
        portCapacity = new Semaphore(Integer.parseInt(split[1]));
        availableContainers = new Semaphore(Integer.parseInt(split[2]));
    }

    public static Port getInstance() {
        return instance;
    }

    public void arrive() throws ThreadException {
        try {
            logger.info("Ship {} arrived.", Thread.currentThread().getName());
            piers.acquire();
        } catch (InterruptedException e) {
            logger.error("There is an error in arrival.");
            throw new ThreadException();
        }
    }

    public void depart() {
        logger.info("Ship {} departed", Thread.currentThread().getName());
        piers.release();
    }

    public void unload(int containersNumber) throws InterruptedException {
        logger.info("Ship {}  unloaded with {}", Thread.currentThread().getName(), containersNumber);
        TimeUnit.SECONDS.sleep(3);
        portCapacity.release(containersNumber);
        logger.info("Warehouse is {}", portCapacity.availablePermits());
    }

    public void load(int containersNumber) throws InterruptedException {
        if (portCapacity.tryAcquire(containersNumber, 1000, TimeUnit.MILLISECONDS)) {
            logger.info("Ship {}  unloaded with {}", Thread.currentThread().getName(), containersNumber);
            logger.info("Warehouse is {}", portCapacity.availablePermits());
            TimeUnit.SECONDS.sleep(3);
        } else {
            logger.warn("Ship {} cannot be loaded, leaving.", Thread.currentThread().getName());
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Port: ")
                .append("piers = ")
                .append(piers)
                .append(", port capacity = ")
                .append(portCapacity)
                .append(", available containers = ")
                .append(availableContainers).toString();
    }
}
