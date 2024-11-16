package system.fullfillment.exceptions;

public class NotFoundProductEntryException extends RuntimeException {
    public NotFoundProductEntryException(String message) {
        super(message);
    }
}
