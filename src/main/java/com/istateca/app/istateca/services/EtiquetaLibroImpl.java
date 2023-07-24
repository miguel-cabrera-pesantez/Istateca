package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.EtiquetaLibroRepository;
import com.istateca.app.istateca.models.EtiquetaLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtiquetaLibroImpl extends BaseServiceImpl<EtiquetaLibro, Integer> implements EtiquetaLibroService {

    @Autowired
    private EtiquetaLibroRepository repository;

    public EtiquetaLibroImpl(BaseRepository<EtiquetaLibro, Integer> baseRepository) {
        super(baseRepository);
    }


}
