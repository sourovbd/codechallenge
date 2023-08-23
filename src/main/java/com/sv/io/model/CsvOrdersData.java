package com.sv.io.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSV Oders Data Import Model
 *
 * @author atequer_rahman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvOrdersData {

    @CsvBindByPosition(position = 0)
    private String orderId;

    @CsvBindByPosition(position = 1)
    private String date;

    @CsvBindByPosition(position = 2)
    private String time;

}
