package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.service.EmpleadoService;
import com.udea.exchangehouse.service.EmpresaService;
import com.udea.exchangehouse.service.MovimientoService;
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

    @Autowired
    MovimientoService movimientoService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping("/enterprises/{id}")
    public Optional<Empresa> empresaPorId(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresabyId(id);
    }

    @PostMapping("/enterprises/{id}")
    public Empresa empresaPorId(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa emp = this.empresaService.getEmpresabyId(id).get();
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

    @PatchMapping("/empleados/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl = this.empleadoService.getEmpleadoById(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setEmail(empleado.getEmail());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return this.empleadoService.saveOrUpdateEmpleado(empl);

    }

    @DeleteMapping("/empleados/{id}")
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta = this.empleadoService.deteleEmpleado(id);
        if(respuesta){
            return "Se pudo eliminar correctamente el empleado con id " + id;
        }
        return "No se pudo eliminar correctamente el empleado con id " + id;
    }

    //Movimientos

    @GetMapping("/movimientos")
    public List<MovimientoDinero> verMovimientos(){
        return this.movimientoService.getAllMovimientos();
    }

    @PostMapping("/movimientos")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimientoDinero){
        return this.movimientoService.saveOrUpdateMovimiento(movimientoDinero);
    }

    @GetMapping("/movimientos/{id}")
    public Optional<MovimientoDinero> movimientoPorId(@PathVariable("id") Integer id){
        return this.movimientoService.getMovimientoById(id);
    }

    @PatchMapping("/movimientos/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = this.movimientoService.getMovimientoById(id).get();
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setMonto(movimientoDinero.getMonto());
        mov.setUsuario(movimientoDinero.getUsuario());
        return this.movimientoService.saveOrUpdateMovimiento(mov);
    }

    @DeleteMapping("/movimientos/{id}")
    public String eliminarMovimiento(@PathVariable("id") Integer id){
        boolean resultado = this.movimientoService.deleteMovimiento(id);
        if(resultado){
            return "Se ha eliminado correctamente el movimiento con id " +  id;
        }
        return "No se ha eliminado correctamente el movimiento con id " + id;
    }

    @GetMapping("/empleados/{id}/movimientos")
    public ArrayList<MovimientoDinero> movimientosPorEmpleado(@PathVariable("id") Integer id){
        return (ArrayList<MovimientoDinero>) this.movimientoService.obtenerPorEmpleado(id);
    }

    @GetMapping("/enterprises/{id}/movimientos")
    public ArrayList<MovimientoDinero> movimientosPorEmpresa(@PathVariable("id") Integer id){
        return (ArrayList<MovimientoDinero>) this.movimientoService.obtenerPorEmpresa(id);
    }
}
