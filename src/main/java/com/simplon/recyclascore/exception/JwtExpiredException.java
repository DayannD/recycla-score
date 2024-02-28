package com.simplon.recyclascore.exception;


public class JwtExpiredException extends Exception {
  public JwtExpiredException(String msg) {
    super(msg);
  }

  public JwtExpiredException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
