package com.dwt.tddcata.dateutils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilTest {



    @Test
    public void testNowAsStr(){
        var myDateTime =  LocalDateTime.of(2019,10,1,8,25,33,1);
        var dateUtil =  new DateUtil();
        dateUtil.nowSupplier = ()-> myDateTime;


        var now = dateUtil.nowAsStr();

        System.out.println(now);
        assertThat(now)
                .isEqualTo("2019-10-01T08:25:33.000000001");
    }
}