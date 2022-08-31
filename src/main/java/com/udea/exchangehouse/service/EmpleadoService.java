package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados(){
        return (List<Empleado>) this.empleadoRepository.findAll();
    }

    public Optional<Empleado> getEmpleadoById(Integer id){
        return this.empleadoRepository.findById(id);
    }

    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return this.empleadoRepository.findByEmpresa(id);
    }

    public Empleado saveOrUpdateEmpleado(Empleado empleado){
        return this.empleadoRepository.save(empleado);
    }

    public boolean deteleEmpleado(Integer id){
        this.empleadoRepository.deleteById(id);
        if(this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }


}
