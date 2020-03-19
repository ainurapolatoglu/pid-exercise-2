package com.sda.pid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceApp {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        Scanner in = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to Members App!");

        Members members = null;  //members - has list of members
        LocalDate attendanceDate = LocalDate.now();
        try {
            String fileName = "member-list.JSON";         // create name if file
            URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);     // address where file is located
            if (resource == null) {  //if that file is not there
                System.out.println("There is no such file exists!: " + fileName);
            } else {
                File jsonFile = new File(resource.getFile());        // create new variable and assign json file to it
                members = mapper.readValue(jsonFile, Members.class);   // creates object of members from file and assigns it to variable
                System.out.println(fileName + " file is loaded.");
            }
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }

        do {
            System.out.println(" ");
            System.out.println("Pick an option:");
            System.out.println("(1) Show all members.");
            System.out.println("(2) Take attendance of members.");
            System.out.println("(3) Show attendance of members.");
            System.out.println("(4) .");
            System.out.println("(5) .");
            System.out.println("(6) Quit.");

            option = Integer.parseInt(in.nextLine());
            if (option == 1) {  //Show members from file
                if (members == null) {  // if cant open, if smth wrong with file, but we just print at this moments that couldnt find members
                    System.out.println("There are no members found!");
                } else {
                    System.out.println(members);  // uses toString methods from Members to print nicely
                    System.out.println("Duplicate ids:");
                    //finds and prints out duplicated IDs
                    printDuplicates(members);
                }
            } else if (option == 2) { //Take attendance of each member and save into file
                System.out.println("Take attendance for members.");
                System.out.println("Please enter a date to write down attendance. in format YYYY-MM-DD. (Default -> Current Date)");
                String date = in.nextLine();


                if (!date.isEmpty()) {
                    try {
                        attendanceDate = LocalDate.parse(date);
                    } catch (DateTimeParseException d) {
                        System.out.println("Entered date is in the wrong format. Default date was set.");
                    }
                }
                System.out.println(attendanceDate.toString());
                Attendance attendance = new Attendance();
                attendance.setDate(attendanceDate);
                for (Member member : members.getMembers()) {
                    System.out.println(member.getName() + member.getId());

                    System.out.println("If attended: write yes/ Did not attend: write no. (Default or if entered incorrect -> assigned No)");
                    String answer = in.nextLine();

                    if ("yes".equals(answer.toLowerCase())) {
                        attendance.getAttended().add(member);
                    } else {
                        attendance.getUnattended().add(member);
                    }

                    System.out.println(member.getName() + member.getId() + " " + answer);
                    System.out.println(" ");
                }

                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/tmp/attendance.json"), attendance);
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(attendance));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (option == 3) { // Display already saved attendance list
                Attendance attendanceList = null;  //members - has list of members
                try {
                  //  Attendance car = objectMapper.readValue(new URL("file:src/attendance.json"), Attendance.class);
                       // create name if file
                    File attendanceFile = new File("/tmp/attendance.json");     // address where file is located
                    if (attendanceFile== null) {  //if that file is not there
                        System.out.println("There is no such file exists!: " );
                    } else {
                        attendanceList = mapper.readValue(attendanceFile, Attendance.class);   // creates object of members from file and assigns it to variable
                        System.out.println(attendanceFile + " file is loaded.");
                        System.out.println(attendanceList);

                    }
                } catch (IOException e) {
                    System.out.println("Error opening the file: " + e.getMessage());
                }

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

    private static void printDuplicates(Members members) { //Members type
        List<Member> memberList = members.getMembers(); // we get members list from Member and assign them to new variable, because calling a method everytime is time costly and easier to write down next time
        Set<Member> tempSet = new HashSet<>(); // create some temp set to use it for future comparation
        memberList.stream().filter(member -> !tempSet.add(member)).collect(Collectors.toSet()).forEach(member -> {
            System.out.println("Member id: " + member.getId());
            //get list of members.stream them (float around). gpick up member from Members and see if we can add it to set. Uses equals method from Member.java
            // we chose set because set elements has to be unique. so if there is already such member we put it to some Collection and set that Collection to SEt
            // then we loop collection printing out each members.id.
        });
    }
}
