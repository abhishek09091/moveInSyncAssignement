package com.example.moveinSync.moveInSync.model;

public class Seat {

    private Integer seatId;
    private Integer totalSeats;
    private Integer bookedSeats;

    public Seat() {
    }

    public Seat(Integer seatId, Integer totalSeats, Integer bookedSeats) {
        this.seatId = seatId;
        this.totalSeats = totalSeats;
        this.bookedSeats = bookedSeats;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Integer bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
