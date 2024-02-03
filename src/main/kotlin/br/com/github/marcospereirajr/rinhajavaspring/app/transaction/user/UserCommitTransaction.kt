package br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user

import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.Transaction
import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.TransactionRepository

class UserCommitTransaction(private val repository: TransactionRepository) {
    fun execute(userId: String, amount: Double, description: String): Transaction {
        return Transaction.create(userId = userId, amount = amount, description = description)
            .apply { repository.save(this) }
    }
}