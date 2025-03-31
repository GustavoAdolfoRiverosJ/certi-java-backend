package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class EnrolmentDetailPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_enrolment", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_ENROLMENT"))
    private Enrolment enrolment;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_COURSE"))
    private Course course;
}
