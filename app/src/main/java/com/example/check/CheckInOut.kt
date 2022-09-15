package com.example.check

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.check.Constants.Companion.RETARDANT_LOWER_LIMIT
import com.example.check.Constants.Companion.RETARDANT_UPPER_LIMIT

import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

data class CheckInOut(
    val date: LocalDate,
    val teacher: Teacher,
    val checkIn: LocalTime,
    val checkOut: LocalTime,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun isRetardant(currentSchedule: Schedule,
                    permissions: List<Permission>): Boolean {
        val dayOfWeek = date.dayOfWeek
        var minutes=0L
        val scheduleDetails= currentSchedule.getDetails()
        val dayScheduleDetail = scheduleDetails.firstOrNull{ it.dayOfWeek==dayOfWeek}
        if (dayScheduleDetail!=null)
            minutes = dayScheduleDetail.checkIn.until(checkIn, ChronoUnit.MINUTES)

        val status = minutes in RETARDANT_LOWER_LIMIT .. RETARDANT_UPPER_LIMIT &&
                !hasPermission(teacher, date, permissions)
        return (status)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isAbsenceForIncorrectRegistration(
        currentSchedule: Schedule,
        permissions: List<Permission>
    ): Boolean {

        val dayOfWeek = date.dayOfWeek
        var minutes = 0L
        var isCheckOutBefore=false
        val scheduleDetails= currentSchedule.getDetails()
        val dayScheduleDetail = scheduleDetails.firstOrNull{ it.dayOfWeek==dayOfWeek}

        if (dayScheduleDetail != null) {
            minutes = dayScheduleDetail.checkIn.until(checkIn, ChronoUnit.MINUTES)
            isCheckOutBefore = checkOut < dayScheduleDetail.checkOut
        }
        val hasPermission = hasPermission(teacher, date, permissions)
        return ( (minutes > RETARDANT_UPPER_LIMIT &&
                !hasPermission ) ||
                (isCheckOutBefore && !hasPermission)
                )
    }

}