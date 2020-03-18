package com.sda.pid;

import com.fasterxml.jackson.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonRootName("members")
public class Members {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String toString() {
        return members.stream().map(String::valueOf).collect(Collectors.joining("\n", "Members: \n", "")).toString();
    }
}
