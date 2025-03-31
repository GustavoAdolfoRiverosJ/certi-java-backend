package com.mitocode.service.impl;

import com.mitocode.model.Student;
import com.mitocode.repository.IStudentRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepo studentRepo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return studentRepo;
    }
}
