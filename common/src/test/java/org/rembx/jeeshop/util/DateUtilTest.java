package org.rembx.jeeshop.util;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

public class DateUtilTest {

    @Test
    public void testDateToLocalDateTime() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        Date date = sdf.parse("2001.07.04 12:08:56 UTC");
        LocalDateTime localDateTime = DateUtil.dateToLocalDateTime(date);
        assertThat(LocalDateTime.of(2001, Month.JULY, 4, 12, 8, 56)).isEqualTo(localDateTime);
    }
}