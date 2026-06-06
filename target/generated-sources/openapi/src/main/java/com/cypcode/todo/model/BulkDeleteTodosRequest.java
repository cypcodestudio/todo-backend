package com.cypcode.todo.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BulkDeleteTodosRequest
 */

@JsonTypeName("bulkDeleteTodos_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-06T11:21:33.992061+02:00[Africa/Johannesburg]", comments = "Generator version: 7.4.0")
public class BulkDeleteTodosRequest {

  @Valid
  private List<Long> ids = new ArrayList<>();

  public BulkDeleteTodosRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BulkDeleteTodosRequest(List<Long> ids) {
    this.ids = ids;
  }

  public BulkDeleteTodosRequest ids(List<Long> ids) {
    this.ids = ids;
    return this;
  }

  public BulkDeleteTodosRequest addIdsItem(Long idsItem) {
    if (this.ids == null) {
      this.ids = new ArrayList<>();
    }
    this.ids.add(idsItem);
    return this;
  }

  /**
   * Get ids
   * @return ids
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "ids", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ids")
  public List<Long> getIds() {
    return ids;
  }

  public void setIds(List<Long> ids) {
    this.ids = ids;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BulkDeleteTodosRequest bulkDeleteTodosRequest = (BulkDeleteTodosRequest) o;
    return Objects.equals(this.ids, bulkDeleteTodosRequest.ids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ids);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkDeleteTodosRequest {\n");
    sb.append("    ids: ").append(toIndentedString(ids)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

