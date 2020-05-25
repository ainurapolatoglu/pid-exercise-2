package com.sda.pid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sda.pid.jackson.LocalDateDeserializer;
import com.sda.pid.jackson.LocalDateSerializer;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Attendance {

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private List<Member> attended;
    private List<Member> unattended;

    public Attendance() {
        date = LocalDate.now();
        attended = new ArrayList<>();
        unattended = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Member> getAttended() {
        return attended;
    }

    public void setAttended(List<Member> attended) {
        this.attended = attended;
    }

    public List<Member> getUnattended() {
        return unattended;
    }

    public void setUnattended(List<Member> unattended) {
        this.unattended = unattended;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n Date   " + date);
        str.append(attended.stream().map(String::valueOf).collect(Collectors.joining("\n", "\n================================ \nAttended: \n", "\n")).toString());
        str.append(unattended.stream().map(String::valueOf).collect(Collectors.joining("\n", "================================\nUnattended: \n", "\n")).toString());
        return str.toString();

    }


}
