package com.chendehe.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ErrorResult {

  private String errorMessage;
  private String errorDetail;

  public ErrorResult(String errorMessage, String errorDetail) {
    this.errorMessage = errorMessage;
    this.errorDetail = errorDetail;
  }

}
