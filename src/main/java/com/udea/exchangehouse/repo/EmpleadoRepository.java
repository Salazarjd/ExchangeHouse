package com.udea.exchangehouse.repo;

import com.udea.exchangehouse.models.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    @Query(value = "select * from empleado where empresa_id=?1", nativeQuery = true)
    ArrayList<Empleado> findByEmpresa(Integer id);

    Empleado findByEmail(String email);
}
