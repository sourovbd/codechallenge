package com.ehrlich.codechallenge.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSV Order Details Data Import Model
 *
 * @author atequer_rahman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvOrderDetailsData {

    @CsvBindByPosition(position = 0)
    private String orderDetailsId;

    @CsvBindByPosition(position = 1)
    private String orderId;

    @CsvBindByPosition(position = 2)
    private String pizzaId;

    @CsvBindByPosition(position = 3)
    private String quantity;

}
