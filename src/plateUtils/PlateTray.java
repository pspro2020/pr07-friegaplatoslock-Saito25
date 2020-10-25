package plateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class PlateTray {

    private final List<Plate> plates = new ArrayList<>();

    // Objeto "bloqueador", que nos permite asegurar la atomicidad de las operaciones.
    private final Lock lock = new ReentrantLock();

    // Condición sobre el objeto lock, que determinará cuando hay platos en la bandeja.
    private final Condition isEmpty = lock.newCondition();


    public Plate takePlate() throws InterruptedException {
        lock.lock();
        try {
            while (plates.isEmpty()) {
                isEmpty.await();
            }

            return plates.remove(0);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void putPlate(Plate plate) {
        Objects.requireNonNull(plate);

        lock.lock();

        try {
            plates.add(plate);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public List<Plate> getPlates() {
        return plates;
    }
}
