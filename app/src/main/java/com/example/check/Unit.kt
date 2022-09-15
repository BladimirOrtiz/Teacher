package com.example.check

import java.time.LocalDate

fun hasPermission(employee: Teacher, date: LocalDate, permissions: List<Permission>): Boolean {
    val permission = permissions.firstOrNull{it.date==date && it.teacher==employee}
    return (permission!=null)
}