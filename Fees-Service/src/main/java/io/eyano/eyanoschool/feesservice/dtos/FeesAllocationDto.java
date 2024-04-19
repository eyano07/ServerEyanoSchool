package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * FeesAllocationDto class is used to represent the fees allocation data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
@Data @ToString @NoArgsConstructor
public class FeesAllocationDto {
    private Long id;

    @NotEmpty(message = "Empty field not allowed")
    @NotBlank(message = "White field not allowed")
    @Size(max = 50, min=5, message = "The length of the field must be between 4 and 50 characters." )
    private String designation;

    @Max(value = 100, message = "The value must be less than or equal to 100")
    @Min(value = 1, message = "The value must be greater than or equal to 1")
    private double percentage;

    private SchoolYear schoolYear;

    @NotNull (message = "The field is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idSchoolYear;
    @NotNull (message = "The field is required")
    private TypeFeesDto typeFees;

    @JsonIgnore
    private boolean remove;
}
