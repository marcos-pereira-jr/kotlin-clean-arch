package br.com.github.marcospereirajr.rinhajavaspring.infrastructure.datasource.transaction

import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.Transaction
import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.TransactionRepository
import java.util.concurrent.ConcurrentHashMap

class TransactionInMemory(private val transactions: ConcurrentHashMap<String, Transaction>) : TransactionRepository {
    override fun save(transaction: Transaction): Transaction {
        transactions[transaction.id] = transaction
        return transaction
    }

    override fun listAll(id: String): List<Transaction> {
        return transactions.values.toList().filter { t -> t.userId == id };
    }
}