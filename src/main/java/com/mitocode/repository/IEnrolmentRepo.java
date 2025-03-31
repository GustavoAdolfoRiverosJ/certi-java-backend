package com.mitocode.repository;

import com.mitocode.model.Enrolment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEnrolmentRepo extends IGenericRepo<Enrolment, Integer> {

    @Query("FROM Enrolment e JOIN FETCH e.student JOIN FETCH e.details d JOIN FETCH d.course c")
    List<Enrolment> EnrolmentsWithCoursesAndStudents();

}
