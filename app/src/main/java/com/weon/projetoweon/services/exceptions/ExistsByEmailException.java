package com.weon.projetoweon.services.exceptions;

public class ExistsByEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByEmailException(String email){
        super("Email informado já existente. Email: " + email);
    }
    
}
