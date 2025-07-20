package com.mm.example.dbTwo.service;

import com.mm.example.dbTwo.repository.EmployeeRepository;
import com.mm.example.dto.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DbTwoConsumeService {

    private final EmployeeRepository employeeRepository;

    public void consume(List<ApiData> apiDataList) {
        log.info("Consuming data for Db Two: {}", apiDataList);
    }
}
