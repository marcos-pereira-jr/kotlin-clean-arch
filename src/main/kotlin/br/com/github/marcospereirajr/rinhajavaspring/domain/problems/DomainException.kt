package br.com.github.marcospereirajr.rinhajavaspring.domain.problems

import jakarta.validation.Validation

class DomainException(
    violations: Set<Violation>
) : Exception() {
    data class Violation(val field: String, val message: String)
    companion object {
        fun valid(any: Any) {
            val factory = Validation.buildDefaultValidatorFactory()
            val validator = factory.validator
            val violations = validator.validate(any)
            if (violations.isNotEmpty()) {
                throw DomainException(violations.map { v -> Violation(v.propertyPath.toString(), v.message) }.toSet())
            }
        }
    }
}
