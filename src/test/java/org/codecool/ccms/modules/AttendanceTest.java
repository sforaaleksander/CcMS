package org.codecool.ccms.modules;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class AttendanceTest {

    @Test
    public void toStringTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale(Locale.ENGLISH);
        LocalDate date = LocalDate.parse("22-04-2020", formatter);

        Attendance attendance = new Attendance(date, "Zenon", "Miska", Role.MANAGER);

        assertEquals("2020-04-22, Zenon, Miska, MANAGER", String.join(", ", attendance.toStringList()));
    }

}