package hr.truenorth.project.VHSRentalShop.exception;

public class VHSNotFoundException extends RuntimeException{
    public VHSNotFoundException(String message) {
        super(message);
    }

    public VHSNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VHSNotFoundException(Throwable cause) {
        super(cause);
    }
}
