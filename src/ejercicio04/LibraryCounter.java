package ejercicio04;

import java.time.LocalDate;
import java.util.List;

/**
 * Mostrador de la biblioteca. Todos los mostradores usan el mismo administrador de préstamos.
 */
public class LibraryCounter {

    private final String name;
    private final LoanManager manager = LoanManager.INSTANCE;

    public LibraryCounter(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del mostrador no puede estar vacío."
            );
        }

        this.name = name.trim();
    }

    public LoanManager getManager() {
        return manager;
    }

    public void lend(BookCopy copy, String memberId) {
        boolean registered = manager.registerLoan(copy, memberId, LocalDate.now());

        if (registered) {
            System.out.println("[" + name + "] Préstamo registrado: ejemplar " + copy + " al socio " + memberId);
        } else {
            System.out.println("[" + name + "] Préstamo RECHAZADO: el ejemplar " + copy + " ya se encuentra prestado");
        }
    }

    public void processReturn(BookCopy copy) {
        boolean registered = manager.registerReturn(copy);

        if (registered) {
            System.out.println("[" + name + "] Devolución registrada: ejemplar " + copy);
        } else {
            System.out.println("[" + name + "] Devolución RECHAZADA: el ejemplar " + copy + " no tiene un préstamo activo");
        }
    }

    public void showMemberLoans(String memberId) {
        List<Loan> loans = manager.getActiveLoansByMember(memberId);

        System.out.println("[" + name + "] Préstamos activos del socio " + memberId + " (" + loans.size() + "):");

        for (Loan loan : loans) {
            System.out.println("  " + loan);
        }
    }
}