package com.udea.exchangehouse.controller;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpresaControler {

    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model){

        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();

        model.addAttribute("empresas", listaEmpresas);

        return "verEmpresas";
    }
}
