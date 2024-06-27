package com.hei.app.employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.pointing.Attendance;
import com.hei.app.pointing.Pointing;

public class EmployeTest {
    
    @Test
    void valid_working_hour() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rabe = new Employee(
            "Rabe", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(17, 25, 26)); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        List<Attendance> listAtttendanceMay = new ArrayList<>();
        for (int i = 26; i <= 31; i++) {
            listAtttendanceMay.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAtttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAtttendanceJune.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAtttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAtttendanceJuly.add(new Attendance(i, "07:00-17:00"));
        }

        var pointingRabeMay = new Pointing(rabe, calandarMay, listAtttendanceMay);
        var pointingRabeJune = new Pointing(rabe, calandarJune, listAtttendanceJune);
        var pointingRabeJuly = new Pointing(rabe, calandarJuly, listAtttendanceJuly);

        pointingRabeMay.calculAfterPointing();
        pointingRabeJune.calculAfterPointing();
        pointingRabeJuly.calculAfterPointing();
        var totalWorkHoursRabe  = pointingRabeMay.getWorkHours() + pointingRabeJune.getWorkHours() + pointingRabeJuly.getWorkHours();
        assertEquals(420, totalWorkHoursRabe);
    }


    @Test
    void correct_net_salary() {
        var gardien = new Category(CategoryType.normal, 40, 100_000);

        var john = new Employee(
            "John", "Doe", "5678",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        john.setSalary(new Salary(john.getCategory().getSalaryMatche()));
        assertEquals(80_000, john.getSalary().getNet());
    }
}
