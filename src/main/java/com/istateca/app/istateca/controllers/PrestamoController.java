package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Prestamo;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController  extends BaseController<Prestamo> {

        /*
    controladores basicos estan en controlador general
    crear: /autor/crear -> pasar requestbody
    listar: /autor/listar
    buscar: /autor/buscar/{id} -> pasar PathVariable("id")
    editar: /autor/editar/{id} -> pasar requestbody
    */

    @Autowired
    private PrestamoService service;

    @Override
    protected BaseService<Prestamo, Integer> getService() {
        return service;
    }
}
