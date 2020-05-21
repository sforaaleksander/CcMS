package org.codecool.ccms.modules;

import java.time.LocalDate;

public class WorkDay {
    private LocalDate date;
    private int dayId;

    public WorkDay(LocalDate date, int dayId) {
        this.date = date;
        this.dayId = dayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDayId() {
        return dayId;
    }
}
