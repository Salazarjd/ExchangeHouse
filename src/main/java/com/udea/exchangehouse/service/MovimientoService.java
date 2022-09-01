package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.repo.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    MovimientosRepository movimientosRepository;

    public List<MovimientoDinero> getAllMovimientos(){
        return this.movimientosRepository.findAll();
    }

    public Optional<MovimientoDinero> getMovimientoById(Integer id){
        return this.movimientosRepository.findById(id);
    }

    public MovimientoDinero saveOrUpdateMovimiento(MovimientoDinero movimientoDinero){
        return this.movimientosRepository.save(movimientoDinero);
    }

    public boolean deleteMovimiento(Integer id){
        this.movimientosRepository.deleteById(id);
        if(this.movimientosRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public List<MovimientoDinero> obtenerPorEmpleado(Integer id){
        return this.movimientosRepository.findByEmpleado(id);
    }

    public List<MovimientoDinero> obtenerPorEmpresa(Integer id){
        return this.movimientosRepository.findByEmpresa(id);
    }
}
