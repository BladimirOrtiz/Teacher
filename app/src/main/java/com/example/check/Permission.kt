package com.example.check

import java.time.LocalDate

data class Permission (val teacher: Teacher,
                       val date: LocalDate,
                       val justification: String,
)