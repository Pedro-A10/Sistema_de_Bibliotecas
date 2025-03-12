package model.entities.exception;

public class BookNotBorrowedException extends LibraryException {
    public BookNotBorrowedException( String isbn, int userId ) {
        super("O livro com ISBN " + isbn + "Não está alugado pelo usuário: " + userId);
    }
}
