package br.com.github.marcospereirajr.rinhajavaspring.domain.transaction

interface TransactionRepository {
    fun save(transaction: Transaction): Transaction

    fun listAll(id: String): List<Transaction>
}