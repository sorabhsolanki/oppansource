package com.oppan.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OppansourceResponse {

  private int code;
  private String message;
  private Object body;

  public OppansourceResponse(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public OppansourceResponse(int code, String message, Object body) {
    this.code = code;
    this.message = message;
    this.body = body;
  }

  @JsonProperty("code")
  public int getCode() {
    return code;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  @JsonProperty("body")
  public Object getBody() {
    return body;
  }
}
