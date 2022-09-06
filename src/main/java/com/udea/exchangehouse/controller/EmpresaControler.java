package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.service.EmpresaService;
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
        return "redirect:/EditarEmpresa";
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
}
