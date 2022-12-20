package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
public class PerformanceChecker implements Runnable {

    private final String baseUrl;
    private final double readProbability;
    private final CopyOnWriteArrayList<Long> readIdList;
    private final CopyOnWriteArrayList<Long> writeIdList;
    private final AtomicLong getBalanceCount;
    private final AtomicLong changeBalanceCount;


    @Override
    @SneakyThrows
    public void run() {
        HttpClient client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(HttpClient.class);

        while (true) {
            if (ThreadLocalRandom.current().nextDouble() < readProbability) {
                Long targetId = randomFromList(readIdList);
                Long balance = client.getBalance(targetId).get();
                log.info("Balance was got for account with ID = {}: balance = {}. Count of get operations = {}",
                        targetId, balance, getBalanceCount.incrementAndGet());
            } else {
                Long targetId = randomFromList(writeIdList);
                Long balance = client.changeBalance(ChangingDto.of(targetId, 1L)).get();
                log.info("Balance was changed for account with ID = {} : balance = {}. Count of change operations = {}",
                        targetId, balance, changeBalanceCount.incrementAndGet());
            }
        }
    }

    private Long randomFromList(CopyOnWriteArrayList<Long> idList) {
        int randomIndex = ThreadLocalRandom.current().nextInt(idList.size());
        return idList.get(randomIndex);
    }

}