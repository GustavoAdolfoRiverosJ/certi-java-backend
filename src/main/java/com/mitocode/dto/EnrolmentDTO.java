package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.Student;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrolmentDTO {

    @NotNull
    private Integer idEnrolment;

    @NotNull
    private LocalDateTime dateEnrolment;

    @NotNull
    private Student student;

    @NotNull
    private boolean enabledEnrolment;

    @NotNull
    @JsonManagedReference
    private List<EnrolmentDetailDTO> details;
}
