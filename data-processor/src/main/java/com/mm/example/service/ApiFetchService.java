package com.mm.example.service;

import com.mm.example.dbTwo.service.DbTwoConsumeService;
import com.mm.example.dbOne.service.DbOneConsumeService;
import com.mm.example.dto.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiFetchService {

    private final WebClient webClient;
    private final DbOneConsumeService dbOneConsumeService;
    private final DbTwoConsumeService dbTwoConsumeService;

    @Value("${api.url}]")
    private String apiUrl;

    public List<ApiData> fetchDataFromApi() {
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(ApiData.class)
                .doOnNext(data -> log.info("Received Data {}", data))
                .doOnError(e -> log.error("Error Fetching Api Data {} ", e.getMessage()))
                .doOnComplete(() -> log.info("Finished Api Data Fetch ...."))
                .collectList()
                .block();
    }

    public void synAll(){
        var apiDataList = fetchDataFromApi();
        log.info("Fetched {} records from API", apiDataList);
        sendDataToDbOne(apiDataList);
        sendDataToDbTwo(apiDataList);
    }

    @Async
    private void sendDataToDbOne(List<ApiData> apiDataList) {
        log.info("Send Data to Db One.");
        dbOneConsumeService.consume(apiDataList);
    }

    @Async
    private void sendDataToDbTwo(List<ApiData> apiDataList) {
        log.info("Send Data to Db Two.");
        dbTwoConsumeService.consume(apiDataList);
    }

}
