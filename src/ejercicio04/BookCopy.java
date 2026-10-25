package ejercicio04;

/**
 * Ejemplar físico de un libro. Cada ejemplar tiene un identificador único,
 * aunque varios ejemplares compartan el mismo título.
 */
public record BookCopy(String copyId, String title) {

    public BookCopy {
        if (copyId == null || copyId.isBlank()) {
            throw new IllegalArgumentException("El identificador del ejemplar no puede estar vacío.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        copyId = copyId.trim();
        title = title.trim();
    }

    @Override
    public String toString() {
        return copyId + " ('" + title + "')";
    }
}
