package com.mitocode.service.impl;

import com.mitocode.model.Enrolment;
import com.mitocode.repository.IEnrolmentRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IEnrolmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrolmentServiceImpl extends CRUDImpl<Enrolment, Integer> implements IEnrolmentService {

    private final IEnrolmentRepo enrolmentRepo;

    @Override
    protected IGenericRepo<Enrolment, Integer> getRepo() {
        return enrolmentRepo;
    }

    public Map<String, List<String>> getCourseToStudent() {
        return enrolmentRepo.EnrolmentsWithCoursesAndStudents().stream()
                .flatMap(enrolment ->
                        enrolment.getDetails().stream().map(detail ->
                                Map.entry(detail.getCourse().getName(),
                                        enrolment.getStudent().getFirstName() + " " + enrolment.getStudent().getLastName())
                        )
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}
