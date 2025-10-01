package com.IMSApi.Inventory.Management.System.API.Exceptions;

public class InvalidStockException extends RuntimeException{
    public InvalidStockException(String message){
        super(message);
    }
}
