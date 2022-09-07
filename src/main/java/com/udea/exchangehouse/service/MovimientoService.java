package com.udea.exchangehouse.service;

import com.udea.exchangehouse.models.MovimientoDinero;
import com.udea.exchangehouse.repo.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    public List<MovimientoDinero> getAllMovimientos(){
        return this.movimientoRepository.findAll();
    }

    public Optional<MovimientoDinero> getMovimientoById(Integer id){
        return this.movimientoRepository.findById(id);
    }

    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero){
        MovimientoDinero mov = this.movimientoRepository.save(movimientoDinero);
        if(this.movimientoRepository.findById(mov.getId()).isPresent()){
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){
        this.movimientoRepository.deleteById(id);
        if(this.movimientoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public List<MovimientoDinero> obtenerPorEmpleado(Integer id){
        return this.movimientoRepository.findByEmpleado(id);
    }

    public List<MovimientoDinero> obtenerPorEmpresa(Integer id){
        return this.movimientoRepository.findByEmpresa(id);
    }
}
