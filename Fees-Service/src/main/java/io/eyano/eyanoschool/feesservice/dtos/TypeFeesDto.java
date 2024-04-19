package io.eyano.eyanoschool.feesservice.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * TypeFeesDto class is used to represent the type fees data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
@Data @ToString @NoArgsConstructor
public class TypeFeesDto  {
    private Long id;
    @NotEmpty(message = "Empty field not allowed")
    @NotBlank(message = "White field not allowed")
    @Size(max = 50, min=5, message = "The length of the field must be between 4 and 50 characters." )
    private String designation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;
}
