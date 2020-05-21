package org.codecool.ccms.modules;

import java.time.LocalDate;

public class WorkDay {
    private final LocalDate date;

    public WorkDay(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
