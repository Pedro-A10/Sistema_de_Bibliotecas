package model.entities.exception;

public class DuplicateBookException extends LibraryException {
    public DuplicateBookException( String isbn ) {
        super("O livo com esse ISBN: " + isbn + "Já está cadastrado.");
    }
}
