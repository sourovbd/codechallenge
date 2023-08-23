package com.sv.io.service;

import com.sv.io.Entity.OrderDetails;
import com.sv.io.Entity.Orders;
import com.sv.io.Entity.Pizza;
import com.sv.io.Entity.PizzaTypes;
import com.sv.io.exception.GlobalException;
import com.sv.io.repository.OrderDetailsCsvImportRepository;
import com.sv.io.repository.OrdersCsvImportRepository;
import com.sv.io.repository.PizzaCsvImportRepository;
import com.sv.io.repository.PizzaTypesCsvImportRepository;
import com.sv.io.validation.strategy.OrderDetailsCsvDataMappingStrategy;
import com.sv.io.validation.strategy.OrdersCsvDataMappingStrategy;
import com.sv.io.validation.strategy.PizzaCsvDataMappingStrategy;
import com.sv.io.validation.strategy.PizzaTypesCsvDataMappingStrategy;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service Class for processing import CSV files
 *
 * @author atequer_rahman
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenCsvServiceImpl implements OpenCsvService {

    private final PizzaCsvImportRepository pizzaCsvImportRepository;
    private final PizzaTypesCsvImportRepository pizzaTypesCsvImportRepository;
    private final OrdersCsvImportRepository ordersCsvImportRepository;
    private final OrderDetailsCsvImportRepository orderDetailsCsvImportRepository;

    public void importPizzaCsvData(MultipartFile csvFile) throws GlobalException, IOException {

        log.info("START importPizzaCsvData: ");

        CSVReader csvReader = new CSVReaderBuilder(new UnicodeReader(csvFile.getInputStream()))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();

        CsvToBean<Pizza> csvToBean = new CsvToBeanBuilder<Pizza>(csvReader)
                .withOrderedResults(true)
                .withSkipLines(1)
                .withType(Pizza.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .withMappingStrategy(new PizzaCsvDataMappingStrategy<>())
                .withIgnoreQuotations(true)
                .withThrowExceptions(true)
                .build();

        List<Pizza> pizzaList = csvToBean.parse();
        pizzaCsvImportRepository.saveAll(pizzaList.parallelStream()
                .map(pizza -> {
                    pizza.setPizzaTypesId(pizzaTypesCsvImportRepository.findByPizzaTypeId(pizza.getPizzaTypeId()).getId());
                    return pizza;
                })
                .collect(Collectors.toList()));

        log.info("END importPizzaCsvData");
    }

    @Override
    public void importPizzaTypesCsvData(MultipartFile csvFile) throws GlobalException, IOException {

        log.info("START importPizzaTypesCsvData: ");

        byte[] fileContent = csvFile.getBytes();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new ByteArrayInputStream(fileContent), StandardCharsets.UTF_8))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();

        CsvToBean<PizzaTypes> csvToBean = new CsvToBeanBuilder<PizzaTypes>(csvReader)
                .withOrderedResults(true)
                .withSkipLines(1)
                .withType(PizzaTypes.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .withMappingStrategy(new PizzaTypesCsvDataMappingStrategy<>())
                .withIgnoreQuotations(true)
                .withThrowExceptions(true)
                .build();

        List<PizzaTypes> pizzaTypesList = csvToBean.parse();
        pizzaTypesCsvImportRepository.saveAll(pizzaTypesList);

        log.info("END importPizzaTypesCsvData");
    }

    @Override
    public void importOrdersCsvData(MultipartFile csvFile) throws GlobalException, IOException {

        log.info("START importOrdersCsvData: ");

        byte[] fileContent = csvFile.getBytes();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new ByteArrayInputStream(fileContent), StandardCharsets.UTF_8))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();

        CsvToBean<Orders> csvToBean = new CsvToBeanBuilder<Orders>(csvReader)
                .withOrderedResults(true)
                .withSkipLines(1)
                .withType(Orders.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .withMappingStrategy(new OrdersCsvDataMappingStrategy<>())
                .withIgnoreQuotations(true)
                .withThrowExceptions(true)
                .build();

        List<Orders> ordersList = csvToBean.parse();
        ordersCsvImportRepository.saveAll(ordersList);

        log.info("END importOrdersCsvData");
    }

    @Override
    public void importOrderDetailsCsvData(MultipartFile csvFile) throws GlobalException, IOException {
        log.info("START importOrderDetailsCsvData: ");

        CSVReader csvReader = new CSVReaderBuilder(new UnicodeReader(csvFile.getInputStream()))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();

        CsvToBean<OrderDetails> csvToBean = new CsvToBeanBuilder<OrderDetails>(csvReader)
                .withOrderedResults(true)
                .withSkipLines(1)
                .withType(OrderDetails.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .withMappingStrategy(new OrderDetailsCsvDataMappingStrategy<>())
                .withIgnoreQuotations(true)
                .withThrowExceptions(true)
                .build();

        List<OrderDetails> orderDetailsList = csvToBean.parse();

        orderDetailsCsvImportRepository.saveAll(orderDetailsList.parallelStream()
                .map(orderDetails -> {
                    orderDetails.setOrdersId(ordersCsvImportRepository.findByOrderId(orderDetails.getOrderId()).getId());
                    return orderDetails;
                })
                .collect(Collectors.toList()));

        log.info("END importOrderDetailsCsvData");
    }
}
