package com.IMSApi.Inventory.Management.System.API.GlobalExceptionHandelers;

import com.IMSApi.Inventory.Management.System.API.Exceptions.InvalidStockException;
import com.IMSApi.Inventory.Management.System.API.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InvalidClassException;
@RestControllerAdvice
public class GlobalExceptionHandeler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity handleInvalidStock(InvalidStockException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}


