package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    @NotNull
    @Min(value = 1)
    private Integer idCourse;

    @NotNull
    private String nameCourse;

    @NotNull
    private String acronymCourse;

    @NotNull
    private boolean enabledCourse;
}
