package br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user

import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.Transaction
import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.TransactionRepository

class UserListAllTransaction(private val repository: TransactionRepository) {
    fun execute(id: String): List<Transaction> {
        return repository.listAll(id);
    }
}