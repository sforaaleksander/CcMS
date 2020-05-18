package org.codecool.ccms.modules;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Attendance {
    private Map<LocalDateTime, Boolean> calendar;

    public Attendance(){
        calendar = new HashMap<>();
    }

    public Map<LocalDateTime, Boolean> getCalendar() {
        return calendar;
    }
}
