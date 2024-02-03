package br.com.github.marcospereirajr.rinhajavaspring.infrastructure.configuration

import br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user.UserCommitTransaction
import br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user.UserListAllTransaction
import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.Transaction
import br.com.github.marcospereirajr.rinhajavaspring.domain.transaction.TransactionRepository
import br.com.github.marcospereirajr.rinhajavaspring.infrastructure.datasource.transaction.TransactionInMemory
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import java.util.concurrent.ConcurrentHashMap


@Configuration
class SpringContainers {
    private val logger = LoggerFactory.getLogger(SpringContainers::class.java)

    @Configuration
    class DataSources {

        private val dataSourceLogger = LoggerFactory.getLogger(DataSources::class.java)

        @Bean
        @Scope("singleton")
        @Profile("in-memory")
        fun transactionInMemory(): ConcurrentHashMap<String, Transaction> {
            dataSourceLogger.info("Creating in-memory transaction data source")
            return ConcurrentHashMap()
        }

        @Bean
        @Profile("in-memory")
        fun transactionRepository(transactionInMemory: ConcurrentHashMap<String, Transaction>): TransactionRepository {
            dataSourceLogger.info("Creating in-memory transaction repository")
            return TransactionInMemory(transactionInMemory)
        }
    }

    @Bean
    fun userCommitTransaction(transactionRepository: TransactionRepository): UserCommitTransaction {
        logger.info("Creating UserCommitTransaction bean")
        return UserCommitTransaction(transactionRepository)
    }

    @Bean
    fun userListAllTransaction(transactionRepository: TransactionRepository): UserListAllTransaction {
        logger.info("Creating UserListAllTransaction bean")
        return UserListAllTransaction(transactionRepository)
    }
}