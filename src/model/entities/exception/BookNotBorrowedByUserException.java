package model.entities.exception;

public class BookNotBorrowedByUserException extends LibraryException {
    public BookNotBorrowedByUserException( String isbn ) {
        super("O usuário não possui um livro com esse ISBN: " + isbn + "em sua lista de livros alugados.");
    }

    public BookNotBorrowedByUserException(String isbn, int userId) {
      super("Usuário com id: " + userId + "Não possui o livro com o ISBN" + isbn + ".");
    }
}
