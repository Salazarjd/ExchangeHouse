package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.service.EmpleadoService;
import com.udea.exchangehouse.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpresaControler {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping("/enterprises/{id}")
    public Empresa empresaPorId(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresabyId(id);
    }

    @PostMapping("/enterprises/{id}")
    public Empresa empresaPorId(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa emp = this.empresaService.getEmpresabyId(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNit(empresa.getNit());
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @DeleteMapping("/enterprises/{id}")
    public String deleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if (!respuesta){
            return "No se ha eliminado la empresa";
        }
        return "Se eliminó la empresa con id: " + id;
    }

    @GetMapping("/empleados")
    public List<Empleado> verEmpleados(){
        return this.empleadoService.getAllEmpleados();
    }

    @PostMapping("/empleados")
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empleado){
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empleado));
    }

    @GetMapping("/empleados/{id}")
    public Optional<Empleado> empleadoPorId(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }

    @GetMapping("/enterprises/{id}/empleados")
    public ArrayList<Empleado> empleadosPorEmpresa(@PathVariable Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }
}
