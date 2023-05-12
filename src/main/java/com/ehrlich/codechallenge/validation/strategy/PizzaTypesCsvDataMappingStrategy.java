package com.ehrlich.codechallenge.validation.strategy;

import com.ehrlich.codechallenge.Entity.PizzaTypes;
import com.opencsv.CSVReader;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class PizzaTypesCsvDataMappingStrategy<T extends PizzaTypes> implements MappingStrategy<T> {


    public PizzaTypesCsvDataMappingStrategy() {

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
        log.info("PROCESS: starting populateNewBean for CsvPizzaTypesData");

        PizzaTypes csvPizzaTypesData = PizzaTypes.builder()
                .pizzaTypeId(line[0])
                .name(line[1])
                .category(line[2])
                .ingredients(line[3])
                .build();

        log.info("PROCESS: ending populateNewBean for CsvPizzaTypesData");
        return (T) csvPizzaTypesData;
    }
}
