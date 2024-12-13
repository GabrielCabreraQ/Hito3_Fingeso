package com.easywheels.Controller;

import com.easywheels.Model.Catalogo;
import com.easywheels.Service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @RequestMapping
    List<Catalogo> obtenerCatalogos() {
        return catalogoService.obtenerTodosLosCatalogos();
    }

    @PostMapping("/{id}")
    Catalogo obtenerCatalogo(@PathVariable long id) {
        return catalogoService.obtenerCatalogoById(id);
    }
}
