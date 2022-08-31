package com.udea.exchangehouse.repo;

import com.udea.exchangehouse.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpresaRepository extends JpaRepository <Empresa, Integer> {

}
