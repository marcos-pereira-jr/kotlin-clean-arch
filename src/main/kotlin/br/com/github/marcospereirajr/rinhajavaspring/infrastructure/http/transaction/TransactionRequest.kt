package br.com.github.marcospereirajr.rinhajavaspring.infrastructure.http.transaction

import jdk.jfr.DataAmount
import jdk.jfr.Description

data class TransactionRequest(
    val amount: Double,
    val description: String
)
