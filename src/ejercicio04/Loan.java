package ejercicio04;

import java.time.LocalDate;

/**
 * Préstamo activo de un ejemplar a un socio en una fecha determinada.
 */
public record Loan(BookCopy copy, String memberId, LocalDate loanDate) {

    @Override
    public String toString() {
        return "Ejemplar " + copy + " prestado al socio " + memberId + " el " + loanDate;
    }
}
