package com.cypcode.todo.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TodoStats
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-06T11:21:33.992061+02:00[Africa/Johannesburg]", comments = "Generator version: 7.4.0")
public class TodoStats {

  private Integer total;

  private Integer completed;

  private Integer active;

  private Integer overdue;

  @Valid
  private Map<String, Integer> byPriority = new HashMap<>();

  public TodoStats total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public TodoStats completed(Integer completed) {
    this.completed = completed;
    return this;
  }

  /**
   * Get completed
   * @return completed
  */
  
  @Schema(name = "completed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("completed")
  public Integer getCompleted() {
    return completed;
  }

  public void setCompleted(Integer completed) {
    this.completed = completed;
  }

  public TodoStats active(Integer active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  */
  
  @Schema(name = "active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("active")
  public Integer getActive() {
    return active;
  }

  public void setActive(Integer active) {
    this.active = active;
  }

  public TodoStats overdue(Integer overdue) {
    this.overdue = overdue;
    return this;
  }

  /**
   * Get overdue
   * @return overdue
  */
  
  @Schema(name = "overdue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("overdue")
  public Integer getOverdue() {
    return overdue;
  }

  public void setOverdue(Integer overdue) {
    this.overdue = overdue;
  }

  public TodoStats byPriority(Map<String, Integer> byPriority) {
    this.byPriority = byPriority;
    return this;
  }

  public TodoStats putByPriorityItem(String key, Integer byPriorityItem) {
    if (this.byPriority == null) {
      this.byPriority = new HashMap<>();
    }
    this.byPriority.put(key, byPriorityItem);
    return this;
  }

  /**
   * Get byPriority
   * @return byPriority
  */
  
  @Schema(name = "byPriority", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("byPriority")
  public Map<String, Integer> getByPriority() {
    return byPriority;
  }

  public void setByPriority(Map<String, Integer> byPriority) {
    this.byPriority = byPriority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoStats todoStats = (TodoStats) o;
    return Objects.equals(this.total, todoStats.total) &&
        Objects.equals(this.completed, todoStats.completed) &&
        Objects.equals(this.active, todoStats.active) &&
        Objects.equals(this.overdue, todoStats.overdue) &&
        Objects.equals(this.byPriority, todoStats.byPriority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, completed, active, overdue, byPriority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TodoStats {\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    overdue: ").append(toIndentedString(overdue)).append("\n");
    sb.append("    byPriority: ").append(toIndentedString(byPriority)).append("\n");
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

