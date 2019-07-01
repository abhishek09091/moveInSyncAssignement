package com.example.moveinSync.moveInSync.model;

import javax.persistence.*;
import java.util.Date;


public class Show {


    private Integer showId;
    private Date date;

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Date getStartTime() {
        return date;
    }

    public void setStartTime(Date date) {
        this.date = date;
    }
}
