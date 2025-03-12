package model.entities.exception;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException( String isbn ) {
       super("Livro com ISBN: " + isbn + "Não encontrado.");
    }
}
