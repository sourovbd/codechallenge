package com.ehrlich.codechallenge.service;

import com.ehrlich.codechallenge.exception.GlobalException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OpenCsvService {

    public void importPizzaCsvData(MultipartFile csvFile) throws GlobalException, IOException;
    public void importPizzaTypesCsvData(MultipartFile csvFile) throws GlobalException, IOException;
    public void importOrdersCsvData(MultipartFile csvFile) throws GlobalException, IOException;
    public void importOrderDetailsCsvData(MultipartFile csvFile) throws GlobalException, IOException;

}
