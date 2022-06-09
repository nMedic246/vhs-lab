package hr.truenorth.project.VHSRentalShop.exception;

public class MultipleRentalsException extends RuntimeException{
    public MultipleRentalsException(String message) {
        super(message);
    }

    public MultipleRentalsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleRentalsException(Throwable cause) {
        super(cause);
    }
}
