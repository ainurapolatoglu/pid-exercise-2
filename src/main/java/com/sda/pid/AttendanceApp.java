package com.sda.pid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class AttendanceApp {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        Scanner in = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to Members App!");

        Members members = null;
        try {

            URL resource = ClassLoader.getSystemClassLoader().getResource("member-list.JSON");
            if (resource == null) {
                System.out.println("There is no such file exists!: " + "member-list-1.JSON");
            } else {
                File jsonFile = new File(resource.getFile());
                members = mapper.readValue(jsonFile, Members.class);
                System.out.println("member-list.json is loaded.");
            }

        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }

        do {
            System.out.println(" ");
            System.out.println("Pick an option:");
            System.out.println("(1) Show all members.");
            System.out.println("(2) .");
            System.out.println("(3) .");
            System.out.println("(4) .");
            System.out.println("(5) .");
            System.out.println("(6) Quit.");

            option = Integer.parseInt(in.nextLine());
            if (option == 1) {
                if (members == null){
                    System.out.println("There are no members found!");
                } else {
                    System.out.println(members);
                }
            } else if (option == 2) {


            } else if (option == 3) {


            } else if (option == 4) {

            } else if (option == 5) {

            } else if (option == 6) {
                System.out.println(" Thank you! Come again!");
                System.out.println(" :D ");
                break;
            } else {
                System.out.println("Entered option doesn't exist. Please enter correct option!");
            }
        } while (option > 0);
        in.close();

        //mapper.writeValue(new File(ClassLoader.getSystemClassLoader().getResource("member-list.JSON").getFile()), Members.class);
        //   members.getMembers().add(new Member("Caglar", "caglar"));
        //  String prettyFile = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(members);
        // System.out.println(prettyFile);

       /* JsonNode root = mapper.readTree(String.valueOf(jsonFile));
        JsonNode name = root.get("name");
        System.out.println("name "+ name.asText()); */

    }
}
