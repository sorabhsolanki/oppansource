package com.oppan.exception;

public class UploadRequestException extends Exception{

  public UploadRequestException(String message) {
    super(message);
  }

  public UploadRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
