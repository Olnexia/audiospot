package by.belstu.losik.exception;

/**
 * {@code EntityNotFoundException} is thrown when a non-existing entity is requested
 */

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
