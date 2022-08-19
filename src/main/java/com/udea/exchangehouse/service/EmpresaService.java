package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.Empresa;
import com.udea.exchangehouse.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Empresa getEmpresabyId(Integer id){
        return empresaRepository.findById(id).get();
    }

    public boolean saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp = empresaRepository.save(empresa);
        if(empresaRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);
        if(getEmpresabyId(id) != null){
            return false;
        }
        return true;
    }
}
