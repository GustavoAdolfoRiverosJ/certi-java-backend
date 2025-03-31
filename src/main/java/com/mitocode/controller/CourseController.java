package com.mitocode.controller;


import com.mitocode.dto.CourseDTO;
import com.mitocode.model.Course;
import com.mitocode.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() throws Exception{
        List<CourseDTO> list = courseService.findAll().stream().map(this::convertToDTO).toList();
        //return ResponseEntity.ok(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") Integer id)  throws Exception{
        Course course = courseService.findById(id);
        return ResponseEntity.ok(convertToDTO(course));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO dto) throws Exception{
        Course obj = courseService.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update( @PathVariable("id") Integer id,@RequestBody CourseDTO dto) throws Exception{
        Course obj = courseService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CourseDTO convertToDTO(Course obj){
        return modelMapper.map(obj, CourseDTO.class);
    }
    private Course convertToEntity(CourseDTO dto){
        return modelMapper.map(dto, Course.class);
    }
}
