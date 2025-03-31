package com.mitocode.controller;


import com.mitocode.dto.RoleDTO;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    //@Autowired
    private final IRoleService roleService;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() throws Exception{
        List<RoleDTO> list = roleService.findAll().stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") Integer id)  throws Exception{
        Role role = roleService.findById(id);
        return ResponseEntity.ok(convertToDTO(role));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO dto) throws Exception{
        Role obj = roleService.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update( @PathVariable("id") Integer id,@RequestBody RoleDTO dto) throws Exception{
        Role obj = roleService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private RoleDTO convertToDTO(Role obj){
        return modelMapper.map(obj, RoleDTO.class);
    }
    private Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }
}
