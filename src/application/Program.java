package application;

import model.entities.Book;
import model.entities.Library;
import model.entities.User;
import model.entities.exception.*;

import java.util.*;

public class Program {

    public static void main( String[] args ) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Sistema de Biblioteca: ");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Alugar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Listar Livros Disponíveis");
            System.out.println("6. Listar Livros Alugados");
            System.out.println("0. Sair");
            System.out.print("Escolha Uma Das Opções: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> cadastrarLivro(library, sc);
                    case 2 -> cadastrarUsuario(library, sc);
                    case 3 -> alugarLivro(library, sc);
                    case 4 -> devolverLivro(library, sc);
                    case 5 -> listarDisponiveis(library);
                    case 6 -> listarAlugados(library);
                    case 0 -> running = false;
                    default -> System.out.println("Opção invalida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida! Use números.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
        System.out.println("Sistema encerrado.");
    }
    private static void cadastrarLivro(Library library, Scanner sc) {
        try {
            System.out.print("Informe o ISBN: ");
            String isbn = sc.nextLine();
            System.out.print("Informe o titulo: ");
            String tittle = sc.nextLine();
            System.out.print("Nome do autor: ");
            String author = sc.nextLine();
            System.out.print("Ano de lançamento: ");
            int year = sc.nextInt();

            Book book = new Book(isbn, tittle, author, year);
            library.addBook(book);
            System.out.println("Livro cadastrado.");
        }catch (DuplicateBookException e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }
    private static void cadastrarUsuario (Library library, Scanner sc) {
        try {
            System.out.print("Crie o ID do usuário: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Nome do usuário: ");
            String name = sc.nextLine();

            User usuario = new User(id, name);
            library.addUser(usuario);
            System.out.println("Usuário cadastrado.");
        }catch (DuplicateUserException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private static void alugarLivro (Library library, Scanner sc) {
        try {
            System.out.print("Informe o ISBN do livro: ");
            String isbn = sc.nextLine();
            System.out.print("Informe o ID do usuário: ");
            int userId = sc.nextInt();

            library.borrowBook(isbn, userId);
            System.out.println("Livro alugado.");
        } catch (BookNotFoundException | UserNotFoundException | BookAlreadyBorrowedException |
                 InvalidOperationException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private static void devolverLivro (Library library, Scanner sc) {
        try {
            System.out.print("ISBN do livro: ");
            String isbn = sc.nextLine();
            System.out.print("Id do usuário: ");
            int userId = sc.nextInt();

            library.returnBook(isbn, userId);
            System.out.println("Livro devolvido com sucesso.");
        }catch (BookNotFoundException | UserNotFoundException | InvalidOperationException
                | BookNotBorrowedException | BookNotBorrowedByUserException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarDisponiveis (Library library) {
        List<Book> disponiveis = library.listAvailableBooks();
        System.out.println("=== Livros disponíveis ===");
        disponiveis.forEach(book -> System.out.println("-" + book.getTittle()
                + " -ISBN: " + book.getIsbn()));
    }

    private static void listarAlugados (Library library) {
        List<Book> books = library.getBooks();
        List<User> users = library.getUsers();

        Map<Book, User> alugados = library.listBorrowedBooks();
        for (User user : users) {
            for (Book book : user.getBorrowedBooks()) {
                alugados.put(book, user);
            }
        }
        if (alugados.isEmpty()) {
            System.out.println("Nenhum livro alugado no momento");
        }else {
            System.out.println("Livros alugados: ");
            alugados.forEach(( book, user ) ->
                    System.out.println("- Livro: " + book.getTittle()
                    + " (ISBN: " + book.getIsbn() + ")"
                    + " Usuário: " + user.getName()
                    +" (ID: " + user.getId() + ")" ));
        }
    }
}
