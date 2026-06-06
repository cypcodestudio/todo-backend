package com.cypcode.todo.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Priority
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-06T11:21:33.992061+02:00[Africa/Johannesburg]", comments = "Generator version: 7.4.0")
public enum Priority {
  
  LOW("LOW"),
  
  MEDIUM("MEDIUM"),
  
  HIGH("HIGH"),
  
  URGENT("URGENT");

  private String value;

  Priority(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Priority fromValue(String value) {
    for (Priority b : Priority.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

