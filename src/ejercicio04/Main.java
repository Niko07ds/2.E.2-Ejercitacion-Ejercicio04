package ejercicio04;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 4: Administrador único de préstamos ===");

        LibraryCounter counterA = new LibraryCounter("Mostrador A");
        LibraryCounter counterB = new LibraryCounter("Mostrador B");

        System.out.println("\n--- Comprobación de identidad ---");
        boolean sameManager = counterA.getManager() == counterB.getManager();
        System.out.println("¿Ambos mostradores usan el mismo administrador? " + (sameManager ? "Sí" : "No"));

        // Dos ejemplares del mismo título, con identificadores distintos
        BookCopy ficciones1 = new BookCopy("EJ-0001", "Ficciones");
        BookCopy ficciones2 = new BookCopy("EJ-0002", "Ficciones");
        BookCopy aleph1 = new BookCopy("EJ-0003", "El Aleph");

        System.out.println("\n--- Préstamo desde el mostrador A ---");
        counterA.lend(ficciones1, "SOCIO-101");

        System.out.println("\n--- El mostrador B intenta prestar el mismo ejemplar ---");
        counterB.lend(ficciones1, "SOCIO-202");

        System.out.println("\n--- El mostrador B presta otro ejemplar del mismo título ---");
        counterB.lend(ficciones2, "SOCIO-202");
        counterA.lend(aleph1, "SOCIO-101");

        System.out.println("\n--- Préstamos activos por socio ---");
        counterA.showMemberLoans("SOCIO-101");
        counterB.showMemberLoans("SOCIO-202");

        System.out.println("\n--- Devolución de un ejemplar sin préstamo activo ---");
        BookCopy neverLent = new BookCopy("EJ-0099", "Rayuela");
        System.out.println("Préstamos activos antes: " + LoanManager.INSTANCE.getActiveLoansCount());
        counterB.processReturn(neverLent);
        System.out.println("Préstamos activos después: " + LoanManager.INSTANCE.getActiveLoansCount());

        System.out.println("\n--- Devolución y nuevo préstamo del ejemplar liberado ---");
        counterA.processReturn(ficciones1);
        counterB.lend(ficciones1, "SOCIO-202");

        System.out.println("\n--- Estado final ---");
        counterA.showMemberLoans("SOCIO-101");
        counterB.showMemberLoans("SOCIO-202");
    }
}
