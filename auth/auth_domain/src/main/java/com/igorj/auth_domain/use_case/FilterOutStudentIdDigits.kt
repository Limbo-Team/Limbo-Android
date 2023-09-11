package com.igorj.auth_domain.use_case

class FilterOutStudentIdDigits {

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}