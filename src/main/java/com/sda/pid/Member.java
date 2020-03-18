package com.sda.pid;

import com.fasterxml.jackson.annotation.*;

import java.util.Map;

@JsonRootName(value = "name")
@JsonPropertyOrder({"name", "id"})
public class Member {
    private String name; //attribute
    private String id; // the rest down are elements


    public Member () {

    }

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

  // @JsonGetter("id")
    public String getId() {
        return id;
    }

   // @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("name: ");
        str.append(name);
        str.append(", id: ");
        str.append(id);
        return str.toString();
    }
}
