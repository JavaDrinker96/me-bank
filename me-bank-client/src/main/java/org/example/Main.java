package org.example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static final String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
        final int threadCount = 11;
        final int readQuota = 5;
        final int writeQuota = 4;
        final List<Long> readIdList = List.of(1L, 2L, 3L);
        final List<Long> writeIdList = List.of(1L, 2L, 3L);

        final AtomicLong getBalanceCount = new AtomicLong();
        final AtomicLong changeBalanceCount = new AtomicLong();

        final double readProbability = (double) readQuota / (double) (readQuota + writeQuota);

        final CopyOnWriteArrayList<Long> readIdListThreadSafe = new CopyOnWriteArrayList<>(readIdList);
        final CopyOnWriteArrayList<Long> writeIdListThreadSafe = new CopyOnWriteArrayList<>(writeIdList);


        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            PerformanceChecker performanceChecker = new PerformanceChecker(BASE_URL, readProbability, readIdListThreadSafe,
                    writeIdListThreadSafe, getBalanceCount, changeBalanceCount);
            executorService.submit(performanceChecker);
        }
        executorService.shutdown();
    }

}