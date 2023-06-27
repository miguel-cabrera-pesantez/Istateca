package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.TerceroPrestamoRepository;
import com.istateca.app.istateca.models.TerceroPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerceroPrestamoImpl extends BaseServiceImpl<TerceroPrestamo, Integer> implements TerceroPrestamoService{

    @Autowired
    private TerceroPrestamoRepository repository;

    public TerceroPrestamoImpl(BaseRepository<TerceroPrestamo, Integer> baseRepository) {
        super(baseRepository);
    }
}
