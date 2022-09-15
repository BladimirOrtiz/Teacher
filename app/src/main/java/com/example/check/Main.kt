package com.example.check

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val teacheradd = Teacher(
        id = 1,
        fullName = "Bladimir de Jesús Ortiz Ramirez",
        academicLevel = AcademicLevel.ASSOCIATE,
        curp = "OIRB990825HOCRML01",
        dateOfAdmission = LocalDate.parse("2014-09-09"),
        budgetKey = "A0998"
    )
    val periodadd = Period(
        initialDate = LocalDate.parse("2022-09-01"),
        finalDate = LocalDate.parse("2022-09-08"), "Agosto-Diciembre 2022"
    )
    //val steacerlis = Schedule()

    val detail1 = Schedule.Detail(
        dayOfWeek = DayOfWeek.THURSDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail2 = Schedule.Detail(
        dayOfWeek = DayOfWeek.FRIDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail3 = Schedule.Detail(
        dayOfWeek = DayOfWeek.MONDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail4 = Schedule.Detail(
        dayOfWeek = DayOfWeek.TUESDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail5 = Schedule.Detail(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail6 = Schedule.Detail(
        dayOfWeek = DayOfWeek.THURSDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val detail7 = Schedule.Detail(
        dayOfWeek = DayOfWeek.FRIDAY,
        checkIn = LocalTime.parse("08:00:00"),
        checkOut = LocalTime.parse("16:00:00")
    )
    val steacher =
        listOf<Schedule.Detail>(detail1, detail2, detail3, detail4, detail5, detail6, detail7)
    val schedule = Schedule.Builder(
        teacher = teacheradd, period = periodadd
    )
        .addOne(scheduleDetail = detail1)
        .addOne(scheduleDetail = detail2)
        .addOne(scheduleDetail = detail3)
        .addOne(scheduleDetail = detail4)
        .addOne(scheduleDetail = detail5)
        .addOne(scheduleDetail = detail6)
        .addOne(scheduleDetail = detail7)

        .build()

    val listOfCheckInOut = listOf<CheckInOut>(
        CheckInOut(
            LocalDate.parse("2022-09-01"),
            teacheradd,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-02"),
            teacheradd,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-05"),
            teacheradd,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-06"),
            teacheradd,
            LocalTime.parse("08:00"),
            LocalTime.parse("15:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-07"),
            teacheradd,
            LocalTime.parse("09:00"),
            LocalTime.parse("16:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-08"),
            teacheradd,
            LocalTime.parse("09:00"),
            LocalTime.parse("16:00")
        )

    )
    val incident = Incident(
        teacheradd,
        schedule,
        listOfCheckInOut,
        listOf(),
        LocalDate.parse("2022-09-01"),
        LocalDate.parse("2022-09-08")
    )
    val haspermision = CheckInOut(
        date = LocalDate.parse("2022-09-01"),
        teacher = teacheradd,
        checkIn = detail1.checkIn,
        checkOut = detail1.checkOut
    )
    val permission1 = Permission(
        teacher = teacheradd,
        date = LocalDate.parse("2022-09-02"),
        justification = "PROBLEMAS DE SALUD"
    )
    val permission2 = Permission(
        teacher = teacheradd,
        date = LocalDate.parse("2022-09-03"),
        justification = "PROBLEMAS DE SALUD"
    )
    val permission3 = Permission(
        teacher = teacheradd,
        date = LocalDate.parse("2022-09-04"),
        justification = "PROBLEMAS DE SALUD"
    )
    val permission4 = Permission(
        teacher = teacheradd,
        date = LocalDate.parse("2022-09-05"),
        justification = "PROBLEMAS DE SALUD"
    )
    val permissionList = listOf<Permission>(permission1, permission2, permission3, permission4)
    val isRetardantVerify = haspermision.isRetardant(schedule, permissionList)
    val verifyisAbsenceForIncorrectRegistration =
        haspermision.isAbsenceForIncorrectRegistration(schedule, permissionList)
    println("El Maestro  ${teacheradd.fullName}" + "${teacheradd.academicLevel}" + "En el periodo  ${periodadd}"+ "CON HORARIO"+ steacher+"TIENE ${isRetardantVerify} RETARTADOS")


}



