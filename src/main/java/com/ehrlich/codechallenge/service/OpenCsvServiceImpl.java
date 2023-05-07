package com.ehrlich.codechallenge.service;

import com.ehrlich.codechallenge.Entity.Pizza;
import com.ehrlich.codechallenge.exception.GlobalException;
import com.ehrlich.codechallenge.model.CsvPizzaData;
import com.ehrlich.codechallenge.repository.PizzaCsvImportRepository;
import com.ehrlich.codechallenge.validation.strategy.ManualImportedCsvDeviceMappingStrategy;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Service Class for processing import CSV file
 *
 * @author atequer_rahman
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenCsvServiceImpl implements OpenCsvService {

    private final PizzaCsvImportRepository pizzaCsvImportRepository;
    public List<CsvPizzaData> importPizzaCsvData(MultipartFile csvFile) throws GlobalException, IOException {

        log.info("START importPizzaCsvData: ");

        Pizza pizza = new Pizza();
        CSVReader csvReader = new CSVReaderBuilder(new UnicodeReader(csvFile.getInputStream()))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();

        CsvToBean<CsvPizzaData> csvToBean = new CsvToBeanBuilder<CsvPizzaData>(csvReader)
                .withOrderedResults(true)
                .withSkipLines(0)
                .withType(CsvPizzaData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .withMappingStrategy(new ManualImportedCsvDeviceMappingStrategy<>(pizzaCsvImportRepository))
                .withIgnoreQuotations(true)
                .withThrowExceptions(true)
                .build();

        List<CsvPizzaData> csvDeviceDataList = new CopyOnWriteArrayList<>();
        csvToBean.forEach(csvPizzaData -> {
            pizza.setPizzaId(csvPizzaData.getPizzaId());
            pizza.setPizzaTypeId(csvPizzaData.getPizzaTypeId());
            pizza.setPrice(csvPizzaData.getPrice());
            pizza.setSize(csvPizzaData.getSize());
            pizzaCsvImportRepository.save(pizza);
        });

        log.info("END importPizzaCsvData");
        return csvDeviceDataList;
    }
}
