package com.ehrlich.codechallenge.service;

import com.ehrlich.codechallenge.exception.GlobalException;
import com.ehrlich.codechallenge.model.CsvPizzaData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OpenCsvService {

    public List<CsvPizzaData> importPizzaCsvData(MultipartFile csvFile) throws GlobalException, IOException;
}
