package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFunctionResponse {

    @JsonProperty(required = true, value = "code")
    @JsonPropertyDescription("code")
    private Integer code;

    @JsonProperty(required = true, value = "msg")
    @JsonPropertyDescription("msg")
    private String msg;

}
