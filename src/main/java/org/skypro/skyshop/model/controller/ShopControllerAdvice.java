package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.shoperror.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductHandler(NoSuchProductException e) {
        ShopError shopError = new ShopError("404", "Product Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(shopError);
    }
}
