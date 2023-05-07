package com.ehrlich.codechallenge.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSV Device Import Model
 *
 * @author atequer_rahman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvPizzaData {

    @CsvBindByPosition(position = 0)
    private String pizzaId;

    @CsvBindByPosition(position = 1)
    private String pizzaTypeId;

    @CsvBindByPosition(position = 2)
    private String size;

    @CsvBindByPosition(position = 3)
    private Long price;

}
