package com.sv.io.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSV Pizza Types Data import Model
 *
 * @author atequer_rahman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvPizzaTypesData {

    @CsvBindByPosition(position = 0)
    private String pizzaTypeId;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String category;

    @CsvBindByPosition(position = 3)
    private String ingredients;

}
