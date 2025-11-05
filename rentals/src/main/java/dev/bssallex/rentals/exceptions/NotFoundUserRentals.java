package dev.bssallex.rentals.exceptions;

public class NotFoundUserRentals extends RuntimeException {
    public NotFoundUserRentals(String message) {
        super(message);
    }
}
