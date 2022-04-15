package com.weon.projetoweon.services.exceptions;

public class ExistsByUserNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByUserNameException(String userName){
        super("Username informado já existente. Username: " + userName);
    }
    
}
