package com.popov314.autoparts.controller.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({
      DataIntegrityViolationException.class,
      MethodArgumentTypeMismatchException.class,
      MethodArgumentNotValidException.class
  })  public String handleDatabaseErrors(Exception ex, Model model) {

    model.addAttribute("message", "Ошибка операции: Введены некорректные данные. " +
        "Возможно, указан несуществующий ID, дубликат записи или превышена длина строки.");

    return "error/500";
  }

}
