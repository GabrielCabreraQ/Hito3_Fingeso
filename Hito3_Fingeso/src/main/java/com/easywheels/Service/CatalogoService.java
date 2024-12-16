package com.easywheels.Service;

import com.easywheels.Model.Catalogo;
import com.easywheels.Repository.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    //Obtener todos los catagolos
    public List<Catalogo> obtenerTodosLosCatalogos() {
        return catalogoRepository.findAll();
    }

    //Obtener un catalogo segun id
    public Catalogo obtenerCatalogoById(long id) {
        return catalogoRepository.findById(id).orElse(null);
    }


}
