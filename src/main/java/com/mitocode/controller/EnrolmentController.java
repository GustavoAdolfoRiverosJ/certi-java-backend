package com.mitocode.controller;


import com.mitocode.dto.EnrolmentDTO;
import com.mitocode.model.Enrolment;
import com.mitocode.service.IEnrolmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrolments")
@RequiredArgsConstructor
public class EnrolmentController {

    private final IEnrolmentService enrolmentService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EnrolmentDTO>> findAll() throws Exception{
        List<EnrolmentDTO> list = enrolmentService.findAll().stream().map(this::convertToDTO).toList();
        //return ResponseEntity.ok(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrolmentDTO> findById(@PathVariable("id") Integer id)  throws Exception{
        Enrolment enrolment = enrolmentService.findById(id);
        return ResponseEntity.ok(convertToDTO(enrolment));
    }

    @PostMapping
    public ResponseEntity<EnrolmentDTO> save(@RequestBody EnrolmentDTO dto) throws Exception{
        Enrolment obj = enrolmentService.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrolmentDTO> update( @PathVariable("id") Integer id,@RequestBody EnrolmentDTO dto) throws Exception{
        Enrolment obj = enrolmentService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        enrolmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list/courses-students")
    public ResponseEntity<Map<String, List<String>>> getCourseToStudentMap() {
        Map<String, List<String>> list = enrolmentService.getCourseToStudent();
        return ResponseEntity.ok(list);
    }

    private EnrolmentDTO convertToDTO(Enrolment obj){
        return modelMapper.map(obj, EnrolmentDTO.class);
    }
    private Enrolment convertToEntity(EnrolmentDTO dto){
        return modelMapper.map(dto, Enrolment.class);
    }
}
