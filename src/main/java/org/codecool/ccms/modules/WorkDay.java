package org.codecool.ccms.modules;

import java.util.Date;

public class WorkDay {
    private Date date;
    private int dayId;

    public WorkDay(Date date, int dayId) {
        this.date = date;
        this.dayId = dayId;
    }

    public Date getDate() {
        return date;
    }

    public int getDayId() {
        return dayId;
    }
}
