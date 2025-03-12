package model.entities.exception;

public class BookCurrentlyBorrowedException extends LibraryException {
    public BookCurrentlyBorrowedException( String isbn ) {
        super("O livro com ISBN " + isbn + "está alugado e não pode ser removido.");
    }
}
