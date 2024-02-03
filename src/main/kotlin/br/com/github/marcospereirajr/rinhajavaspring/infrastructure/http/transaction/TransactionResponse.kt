package br.com.github.marcospereirajr.rinhajavaspring.infrastructure.http.transaction

import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.Transaction
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TransactionResponse(
    val id: String,
    val userId: String,
    val amount: Double,
    val description: String
) {
    constructor(transaction: Transaction) : this(
        transaction.id,
        transaction.userId,
        transaction.amount,
        transaction.description
    )
}
