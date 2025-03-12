package model.entities;

import model.entities.Book;
import model.entities.User;
import model.entities.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books;
    private final List<User> users;

    // Construtor
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) throws DuplicateBookException {
        if (books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()))) {
            throw new DuplicateBookException(book.getIsbn());
        }
        books.add(book);
    }

    // Adiciona um usuário (com validação de ID duplicado)
    public void addUser(User user) throws DuplicateUserException {
        if (users.stream().anyMatch(u -> u.getId() == user.getId())) {
            throw new DuplicateUserException(user.getId());
        }
        users.add(user);
    }

    // Remove um livro (apenas se não estiver emprestado)
    public void removeBook(String isbn) throws BookNotFoundException, BookCurrentlyBorrowedException {
        Book book = findBookByIsbn(isbn);
        if (book.isBorrowed()) {
            throw new BookCurrentlyBorrowedException(isbn);
        }
        books.remove(book);
    }

    public void borrowBook(String isbn, int userId)
            throws BookNotFoundException, UserNotFoundException, BookAlreadyBorrowedException, InvalidOperationException {

        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException(isbn);
        }

        user.borrowBook(book);
        book.borrow();
    }

    // Devolução de livro
    public void returnBook(String isbn, int userId)
            throws BookNotFoundException, UserNotFoundException, BookNotBorrowedException, InvalidOperationException, BookNotBorrowedByUserException {

        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (!user.getBorrowedBooks().contains(book)) {
            throw new BookNotBorrowedException(isbn, userId);
        }

        user.returnBook(book);
        book.returnBook();
    }

    public List<Book> listAvailableBooks() {
        return books.stream()
                .filter(book -> !book.isBorrowed())
                .collect(Collectors.toList());
    }

    public Map<Book, User> listBorrowedBooks() {
        Map<Book, User> borrowedBooks = new HashMap<>();
        for (User user : users) {
            for (Book book : user.getBorrowedBooks()) {
                borrowedBooks.put(book, user);
            }
        }
        return borrowedBooks;
    }

    private Book findBookByIsbn(String isbn) throws BookNotFoundException {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    private User findUserById(int userId) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}