package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(EnrolmentDetailPK.class)
public class EnrolmentDetail {

    @Id
    private Enrolment enrolment;

    @Id
    private Course course;

    @Column(nullable = false)
    private String classroom;

}
