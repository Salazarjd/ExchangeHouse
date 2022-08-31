package com.udea.exchangehouse.repo;

import com.udea.exchangehouse.models.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado,Integer> {

    @Query(value = "select * from empleado where empresa_id=?1", nativeQuery = true)
    ArrayList<Empleado> findByEmpresa(Integer id);
}
