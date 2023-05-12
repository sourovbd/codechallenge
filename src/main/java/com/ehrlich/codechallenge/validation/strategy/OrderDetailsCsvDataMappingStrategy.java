package com.ehrlich.codechallenge.validation.strategy;

import com.ehrlich.codechallenge.Entity.OrderDetails;
import com.opencsv.CSVReader;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class OrderDetailsCsvDataMappingStrategy<T extends OrderDetails> implements MappingStrategy<T> {


    public OrderDetailsCsvDataMappingStrategy() {

    }

    @Override
    public void captureHeader(CSVReader csvReader) {
        // Skip the first line (header) if it exists
        String[] header = new String[0];
        try {
            header = csvReader.readNext();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] generateHeader(T t) {return null;}

    @Override
    public void setType(Class<? extends T> aClass) throws CsvBadConverterException {}

    @Override
    public String[] transmuteBean(T t) {return null;}

    @Override
    public T populateNewBean(String[] line) throws CsvBeanIntrospectionException {
        log.info("PROCESS: starting populateNewBean for csvOrdersData");

        OrderDetails csvOrdersData = OrderDetails.builder()
                .orderDetailsId(Long.parseLong(line[0]))
                .orderId(Long.parseLong(line[1]))
                .pizzaId(line[2])
                .quantity(Long.parseLong(line[2]))
                .build();

        log.info("PROCESS: ending populateNewBean for csvOrdersData");
        return (T) csvOrdersData;
    }
}
