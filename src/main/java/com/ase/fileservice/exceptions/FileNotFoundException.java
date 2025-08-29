package com.ase.fileservice.exceptions;

public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileNotFoundException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message,
        cause,
        enableSuppression,
        writableStackTrace);
  }

  public FileNotFoundException(Throwable cause) {
    super(cause);
  }
}
