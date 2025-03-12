package model.entities.exception;

public class UserNotFoundException extends LibraryException {
    public UserNotFoundException( int userId ) {
        super("Usuário com esse ID: " + userId + "Não encontrado.");
    }
}
