package br.com.github.marcospereirajr.rinhajavaspring.infrastructure.http.user

import br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user.UserCommitTransaction
import br.com.github.marcospereirajr.rinhajavaspring.app.transaction.user.UserListAllTransaction
import br.com.github.marcospereirajr.rinhajavaspring.infrastructure.http.transaction.TransactionRequest
import br.com.github.marcospereirajr.rinhajavaspring.infrastructure.http.transaction.TransactionResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("\${api.user.path}")
@Tag(name = "\${api.user.name}", description = "\${api.user.description}")
class UserController(
    private val userCommitTransaction: UserCommitTransaction,
    private val listAllTransaction: UserListAllTransaction
) {
    @PostMapping("\${api.user.resources.transaction.resources.create.path}")
    @Operation(
        summary = "\${api.user.resources.transaction.resources.create.summary}",
        description = "\${api.user.resources.transaction.resources.create.description}"
    )
    fun makeTransaction(
        @PathVariable("id") userId: String,
        @RequestBody request: TransactionRequest
    ): ResponseEntity<Void> {
        userCommitTransaction.execute(userId, request.amount, request.description);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("\${api.user.resources.transaction.resources.list-all.path}")
    @Operation(
        summary = "\${api.user.resources.transaction.resources.list-all.summary}",
        description = "\${api.clients.resources.transaction.resources.list-all.description}"
    )
    fun listAll(
        @PathVariable("id") id: String,
    ): List<TransactionResponse> {
        return listAllTransaction.execute(id).map { t -> TransactionResponse(t) }.toList()
    }
}