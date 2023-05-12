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

import static com.ehrlich.codechallenge.util.Constants.FILE_UPLOAD_SUCCESSFUL;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CSVImportController {
    private static final String IMPORT_CSV_API_PATH = "/import-csv-file";

    private final OpenCsvService openCsvService;

    @PostMapping(path = IMPORT_CSV_API_PATH)
    public ResponseEntity<Object> importCsvFiles(@RequestPart(value = "pizza_csv_file") @Valid @NotNullFile(message = "Pizza CSV file is missing.")
                                                 @ValidMediaType(value = "text/csv", message = "Pizza CSV file is required.")
                                                 @ValidCharset MultipartFile pizzaCsvFile,
                                                 @RequestPart(value = "pizza_types_csv_file") @Valid @NotNullFile(message = "Pizza Types CSV file is missing.")
                                                 @ValidMediaType(value = "text/csv", message = "Pizza Types CSV file is required.")
                                                 @ValidCharset MultipartFile pizzaTypesCsvFile,
                                                 @RequestPart(value = "orders_csv_file") @Valid @NotNullFile(message = "Orders CSV file is missing.")
                                                 @ValidMediaType(value = "text/csv", message = "Orders CSV file is required.")
                                                 @ValidCharset MultipartFile ordersCsvFile,
                                                 @RequestPart(value = "order_details_csv_file") @Valid @NotNullFile(message = "Order Details CSV file is missing.")
                                                 @ValidMediaType(value = "text/csv", message = "Order Details CSV file is required.")
                                                 @ValidCharset MultipartFile orderDetailsCsvFile) throws GlobalException, IOException {

        log.info("START import csv files.");
        openCsvService.importPizzaTypesCsvData(pizzaTypesCsvFile);
        openCsvService.importPizzaCsvData(pizzaCsvFile);
        openCsvService.importOrdersCsvData(ordersCsvFile);
        openCsvService.importOrderDetailsCsvData(orderDetailsCsvFile);
        log.info("END import csv files.");
        return new ResponseEntity<>(FILE_UPLOAD_SUCCESSFUL, HttpStatus.OK);
    }
}
