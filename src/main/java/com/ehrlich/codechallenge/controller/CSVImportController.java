package com.ehrlich.codechallenge.controller;

import com.ehrlich.codechallenge.exception.GlobalException;
import com.ehrlich.codechallenge.service.OpenCsvService;
import com.ehrlich.codechallenge.validation.annotation.NotNullFile;
import com.ehrlich.codechallenge.validation.annotation.ValidCharset;
import com.ehrlich.codechallenge.validation.annotation.ValidMediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CSVImportController {
    //private static final String IMPORT_CSV_API_PATH = "/import-csv-file";

    private final OpenCsvService openCsvService;

    @PostMapping(path = "/import-csv-file")
    public ResponseEntity<Object> importCsvFile(@RequestPart(value = "pizza_csv_file") @Valid @NotNullFile(message = "Pizza CSV file is missing.")
                                                    @ValidMediaType(value = "text/csv", message = "Pizza CSV file is required.")
                                                    @ValidCharset MultipartFile pizzaCsvFile) throws GlobalException, IOException{

        log.info("START registerDevices: corp_id:{}");
        openCsvService.importPizzaCsvData(pizzaCsvFile);
        log.info("END registerDevices: corp_id:{}");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
