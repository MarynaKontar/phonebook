package com.getjavajob.kovarnevm.phonebook.dao;

public class DAORunTimeException extends RuntimeException {

    public DAORunTimeException(String msg) {
        super(msg);
    }

    public DAORunTimeException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
