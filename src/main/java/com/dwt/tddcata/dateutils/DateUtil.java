package com.dwt.tddcata.dateutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class DateUtil {


    Supplier<LocalDateTime> nowSupplier = LocalDateTime::now;

    public String nowAsStr(){
        return nowSupplier.get().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
