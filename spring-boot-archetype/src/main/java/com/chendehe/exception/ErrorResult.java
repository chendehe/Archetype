package com.chendehe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ErrorResult {

  private String errorMessage;
  private String errorDetail;

}
