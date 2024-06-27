package com.hei.app.pointing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Category;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

public class PointingTest {

    @Test
    void valid_salary_working_hour_without_HM() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of()); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        Set<Attendance> listAttendanceMay = new HashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJune = new HashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJuly = new HashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));

            var pointingRakoto = new Pointing(rakoto,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRakoto.calculAfterPointing();

        // True = 634_000.0
        assertTrue(633_000 < rakoto.getSalary().getBrute() && rakoto.getSalary().getBrute() < 635_000);
    }

    @Test
    void valid_salary_working_hour_with_HM() {
        var gardien = new Category(CategoryType.guardian, 98, 100_000);

        var rabe = new Employee(
            "Rabe", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of()); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        Set<Attendance> listAttendanceMay = new HashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJune = new HashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJuly = new HashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "17:00-07:00"));

            var pointingRabe = new Pointing(rabe,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRabe.calculAfterPointing();

        // True = 824_605.23
        assertTrue(823_000 < rabe.getSalary().getBrute() && rabe.getSalary().getBrute() < 825_000);
//        assertEquals(0, rabe.getSalary().getBrute());
    }

    @Test
    void valid_salary_working_hour_day_with_holiday() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(17, 25, 26)); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        Set<Attendance> listAttendanceMay = new HashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJune = new HashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJuly = new HashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));

            var pointingRakoto = new Pointing(rakoto,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRakoto.calculAfterPointing();

        assertEquals(621_429.0, rakoto.getSalary().getBrute());
    }

    @Test
    void valid_salary_working_hour_night_with_holiday() {
        var gardien = new Category(CategoryType.guardian, 98, 100_000);

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
        
        Set<Attendance> listAttendanceMay = new HashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJune = new HashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJuly = new HashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "17:00-07:00"));

            var pointingRabe = new Pointing(rabe,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRabe.calculAfterPointing();

        assertTrue(800_000 < rabe.getSalary().getBrute() && rabe.getSalary().getBrute() < 810_000);
    }

    @Test
    void pointing_without_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
            rakoto,
            List.of(calandar_juin),
            List.of(Set.of(
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00"),
                new Attendance(24, "07:00-17:00")
            ))
        );

        pointing_rakoto.calculAfterPointing();
        assertEquals( Math.round((14_285.71 * 1.4 * 0.8)  + (14_285.71 * 0.8 * 6)),
                      Math.round(rakoto.getSalary().getNet()));
    }

    @Test
    void pointing_with_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
            rakoto,
            List.of(calandar_juin),
            List.of(Set.of(
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00"),
                new Attendance(24, "07:00-18:00")
            ))
        );

        pointing_rakoto.calculAfterPointing();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void pointing_with_holiday_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
            rakoto,
            List.of(calandar_juin),
            List.of(Set.of(
                new Attendance(17, "07:00-17:00"),
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00")
            ))
        );

        pointing_rakoto.calculAfterPointing();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void pointing_with_night_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 98, 110_000);

        var rabe = new Employee(
            "Rabe", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rabe = new Pointing(
            rabe,
            List.of(calandar_juin),
            List.of(Set.of(
                new Attendance(18, "17:00-07:00"),
                new Attendance(19, "17:00-07:00"),
                new Attendance(20, "17:00-07:00"),
                new Attendance(21, "17:00-07:00"),
                new Attendance(22, "17:00-07:00"),
                new Attendance(23, "17:00-07:00"),
                new Attendance(24, "17:00-07:00")
            ))
        );

        pointing_rabe.calculAfterPointing();
        assertTrue(rabe.getSalary().getBrute() > gardien.getSalaryMatche() * 1.2);
    }
}