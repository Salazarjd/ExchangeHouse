package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public boolean saveOrUpdateEmpleado(Empleado empleado){
        Empleado empl = this.empleadoRepository.save(empleado);
        if(this.empleadoRepository.findById(empl.getId()).isPresent()){
            return true;
        }
        return false;
    }

    public boolean deteleEmpleado(Integer id){
        this.empleadoRepository.deleteById(id);
        if(this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

}