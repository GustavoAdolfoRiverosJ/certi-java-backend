package com.mitocode.service;

import com.mitocode.model.Enrolment;

import java.util.List;
import java.util.Map;

public interface IEnrolmentService extends  ICRUD<Enrolment, Integer>{

    Map<String, List<String>> getCourseToStudent();
}