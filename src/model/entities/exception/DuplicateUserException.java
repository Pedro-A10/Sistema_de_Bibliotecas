package model.entities.exception;

import model.entities.Library;

public class DuplicateUserException extends LibraryException {
  public DuplicateUserException( int userId ) {
    super("Usuário com ID " + userId + " Já está cadastrado.");
  }
}

