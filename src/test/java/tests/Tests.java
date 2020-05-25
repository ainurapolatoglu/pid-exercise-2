package tests;

import com.sda.pid.Attendance;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void test() {

        LocalDate attendanceDate = LocalDate.now();
        Attendance attendance = new Attendance();
        attendanceDate.toString();
        assertEquals("2020-03-19", attendanceDate.toString());
    }
}
