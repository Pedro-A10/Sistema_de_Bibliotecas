package model.entities.exception;

public class BookAlreadyBorrowedException extends LibraryException {
    public BookAlreadyBorrowedException( String isbn ) {
        super("Livro com ISBN: " + isbn + "Já está alugado.");
    }
}
