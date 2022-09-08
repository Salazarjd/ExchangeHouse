package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empleado;
import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.service.EmpleadoService;
import com.udea.exchangehouse.service.EmpresaService;
import com.udea.exchangehouse.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmpresaControler {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    MovimientoService movimientoService;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("empresas", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(this.empresaService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable("id") Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp = this.empresaService.getEmpresabyId(id);
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp")Empresa emp, RedirectAttributes redirectAttributes){
        if(this.empresaService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpresa/" + emp.getId();
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(this.empresaService.deleteEmpresa(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/";
    }

    //Empleado

    @GetMapping("/VerEmpleados")
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empleado> listaEmpleados = this.empleadoService.getAllEmpleados();
        model.addAttribute("emplelist", listaEmpleados);
        model.addAttribute("mensaje", mensaje);
        return "verEmpleados";
    }

    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado empl = new Empleado();
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", "mensaje");
        List<Empresa> listaEmpresas = this.empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "agregarEmpleado";
    }

    @PostMapping("/GuardarEmpleado")
    public String nuevoEmpleado(Empleado empl, RedirectAttributes redirectAttributes){
        if(this.empleadoService.saveOrUpdateEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarEmpleado";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado empl = this.empleadoService.getEmpleadoById(id).get();
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = this.empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("empl") Empleado empl, RedirectAttributes redirectAttributes){
        if(this.empleadoService.saveOrUpdateEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpleado/" + empl.getId();
    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(this.empleadoService.deteleEmpleado(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpleados";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public String verEmpleadosPorEmpresa(@PathVariable Integer id, Model model){
        List<Empleado> listaEmpleados = this.empleadoService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist", listaEmpleados);
        return "verEmpleados";
    }

    //Movimientos

    @GetMapping("/VerMovimientos")
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos = this.movimientoService.getAllMovimientos();
        model.addAttribute("movlist", listaMovimientos);
        model.addAttribute("mensaje", mensaje);
        Long sumaMonto = this.movimientoService.obtenerSuma();
        model.addAttribute("sumaMontos", sumaMonto);
        return "verMovimientos";
    }

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov = new MovimientoDinero();
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", "mensaje");
        List<Empleado> listaEmpleados = this.empleadoService.getAllEmpleados();
        model.addAttribute("emplelist", listaEmpleados);
        return "agregarMovimiento";
    }

    @PostMapping("/GuardarMovimiento")
    public String nuevoMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(this.movimientoService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov = this.movimientoService.getMovimientoById(id).get();
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = this.empleadoService.getAllEmpleados();
        model.addAttribute("emplelist", listaEmpleados);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(this.movimientoService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarMovimiento/" + mov.getId();
    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(this.movimientoService.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimientos";
    }

    @GetMapping("/Empleado/{id}/Movimientos")
    public String verMovimientosPorEmpleado(@PathVariable Integer id, Model model){
        List<MovimientoDinero> listaMovimientos = this.movimientoService.obtenerPorEmpleado(id);
        model.addAttribute("movlist", listaMovimientos);
        Long sumaMonto = this.movimientoService.obtenerSumaPorEmpleado(id);
        model.addAttribute("sumaMontos", sumaMonto);
        return "verMovimientos";
    }

    @GetMapping("/Empresa/{id}/Movimientos")
    public String verMovimientosPorEmpresa(@PathVariable Integer id, Model model){
        List<MovimientoDinero> listaMovimientos = this.movimientoService.obtenerPorEmpresa(id);
        model.addAttribute("movlist", listaMovimientos);
        Long sumaMonto = this.movimientoService.obtenerSumaPorEmpresa(id);
        model.addAttribute("sumaMontos", sumaMonto);
        return "verMovimientos";
    }

}
