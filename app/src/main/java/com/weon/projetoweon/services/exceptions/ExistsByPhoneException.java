package com.weon.projetoweon.services.exceptions;

public class ExistsByPhoneException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistsByPhoneException(String phone){
        super("Telefone informado já existente. Phone: " + phone);
    }
    
}

