package com.mm.example.dbOne.service;

import com.mm.example.dbOne.repository.EmployeeRepository;
import com.mm.example.dto.ApiData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbOneConsumeService {

    private final EmployeeRepository employeeRepository;

    public void consume(List<ApiData> apiDataList) {
    }
}
