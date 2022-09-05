package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Optional<Empresa> getEmpresabyId(Integer id){
        return empresaRepository.findById(id);
    }

    public Empresa saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp = empresaRepository.save(empresa);
        return emp;
    }

    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);
        if(empresaRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
