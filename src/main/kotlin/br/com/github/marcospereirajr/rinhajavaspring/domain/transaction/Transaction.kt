package br.com.github.marcospereirajr.rinhajavaspring.domain.transaction

import br.com.github.marcospereirajr.rinhajavaspring.domain.problems.DomainException.Companion.valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.UUID

class Transaction private constructor(
    val id: String,

    @field:NotNull
    val userId: String,

    @field:Min(1)
    val amount: Double,

    @field:NotBlank
    val description: String
) {
    companion object {
        fun create(userId: String, amount: Double, description: String): Transaction {
            return Transaction(
                id = UUID.randomUUID().toString(),
                userId,
                amount,
                description
            ).apply {
                valid(
                    this
                )
            }
        }
    }
}