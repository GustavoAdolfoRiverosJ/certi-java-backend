package com.mitocode.controller;


import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Student;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() throws Exception{
        List<StudentDTO> list = studentService.findAll().stream().sorted(Comparator.comparing(Student::getAge).reversed()).map(this::convertToDTO).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Integer id)  throws Exception{
        Student student = studentService.findById(id);
        return ResponseEntity.ok(convertToDTO(student));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO dto) throws Exception{
        Student obj = studentService.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update( @PathVariable("id") Integer id,@RequestBody StudentDTO dto) throws Exception{
        Student obj = studentService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private StudentDTO convertToDTO(Student obj){
        return modelMapper.map(obj, StudentDTO.class);
    }
    private Student convertToEntity(StudentDTO dto){
        return modelMapper.map(dto, Student.class);
    }
}
