package by.dobysh.mycache.service;

import by.dobysh.mycache.model.Car;
import com.google.common.primitives.Longs;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class InMemoryCacheWithDelayQueue implements Cache {

    private final ConcurrentHashMap<String, SoftReference<Car>> cache = new ConcurrentHashMap<>();

    public List getCache() {

        List<SoftReference<Car>> list = new ArrayList<>(cache.values());
        List<Car> carsList = new ArrayList<>();
//
        for (SoftReference<Car> softReference: list
             ) {
            carsList.add(softReference.get());

        }

        return carsList;
    }

    private final DelayQueue<DelayedCacheObject> cleaningUpQueue = new DelayQueue<>();

    public InMemoryCacheWithDelayQueue() {
        Thread cleanerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DelayedCacheObject delayedCacheObject = cleaningUpQueue.take();
                    cache.remove(delayedCacheObject.getKey(), delayedCacheObject.getReference());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    @Override
    public void add(String key, Car value, long periodInMillis) {
        if (key == null) {
            return;
        }
        if (value == null) {
            cache.remove(key);
        } else {
            long expiryTime = System.currentTimeMillis() + periodInMillis;
            SoftReference<Car> reference = new SoftReference<>(value);
            cache.put(key, reference);
            cleaningUpQueue.put(new DelayedCacheObject(key, reference, expiryTime));
        }
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Car get(String key) {
        return Optional.ofNullable(cache.get(key)).map(SoftReference::get).orElse(null);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public long size() {
        return cache.size();
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private static class DelayedCacheObject implements Delayed {

        @Getter
        private final String key;
        @Getter
        private final SoftReference<Car> reference;
        private final long expiryTime;

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expiryTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Longs.compare(expiryTime, ((DelayedCacheObject) o).expiryTime);
        }
    }
}