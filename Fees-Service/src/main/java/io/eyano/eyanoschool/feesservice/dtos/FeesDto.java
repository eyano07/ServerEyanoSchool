package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.ClassFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * FeesDto class is used to represent the fees data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */

@Data @NoArgsConstructor @ToString
public class FeesDto {
    private Long id;

    @NotEmpty(message = "Empty field not allowed")
    @NotBlank(message = "White field not allowed")
    @Size(max = 50, min=5, message = "The length of the field must be between 4 and 50 characters." )
    private String designation;

    private Double amount;

    private SchoolYear schoolYear;
    @NotNull(message = "The field is required")
    private Long idSchoolYear;

    private ClassFees classFees;
    @NotNull (message = "The field is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idClassFess;

    @NotNull (message = "The field is required")
    private TypeFeesDto typeFees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;
}
