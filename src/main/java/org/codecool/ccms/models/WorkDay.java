package org.codecool.ccms.models;

import java.time.LocalDate;

public class WorkDay implements Displayable {
    private final LocalDate date;

    public WorkDay(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String[] toStringList() {
        return new String[]{date.toString()};
    }
}
