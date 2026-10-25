package ejercicio04;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Administrador único de préstamos.
 * Singleton implementado con enum: Java garantiza una sola instancia (INSTANCE).
 */
public enum LoanManager {

    INSTANCE;

    // Préstamos activos, indexados por el identificador del ejemplar
    private final Map<String, Loan> activeLoans = new LinkedHashMap<>();

    /**
     * Registra un préstamo.
     * @return true si se registró; false si el ejemplar ya estaba prestado
     */
    public boolean registerLoan(BookCopy copy, String memberId, LocalDate loanDate) {
        if (copy == null) {
            throw new IllegalArgumentException("El ejemplar no puede ser nulo.");
        }
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException("El socio no puede estar vacío.");
        }
        if (loanDate == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (activeLoans.containsKey(copy.copyId())) {
            return false; // ya está prestado: no se modifica el estado
        }
        activeLoans.put(copy.copyId(), new Loan(copy, memberId.trim(), loanDate));
        return true;
    }

    /**
     * Registra la devolución de un ejemplar.
     * @return true si se devolvió; false si no tenía un préstamo activo (sin modificar el estado)
     */
    public boolean registerReturn(BookCopy copy) {
        if (copy == null) {
            throw new IllegalArgumentException("El ejemplar no puede ser nulo.");
        }
        return activeLoans.remove(copy.copyId()) != null;
    }

    public boolean isOnLoan(BookCopy copy) {
        return copy != null && activeLoans.containsKey(copy.copyId());
    }

    // Préstamos activos de un socio (copia no modificable)
    public List<Loan> getActiveLoansByMember(String memberId) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException(
                    "El identificador del socio no puede estar vacío."
            );
        }

        String normalizedMemberId = memberId.trim();

        return activeLoans.values().stream()
                .filter(loan -> loan.memberId().equals(normalizedMemberId))
                .toList();
    }

    public int getActiveLoansCount() {
        return activeLoans.size();
    }
}
