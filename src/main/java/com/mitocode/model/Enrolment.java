package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEnrolment;

    @Column(nullable = false)
    private LocalDateTime dateEnrolment;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLMENT_STUDENT"))
    private Student student;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "enrolment", cascade = CascadeType.ALL)
    private List<EnrolmentDetail> details;


}
