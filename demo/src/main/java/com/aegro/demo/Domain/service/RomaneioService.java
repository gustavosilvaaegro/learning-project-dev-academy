package com.aegro.demo.Domain.service;

import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.InterfaceAdpter.Repository.Repository.RomaneioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RomaneioService {

    @Autowired
    private RomaneioRepository romaneioRepository;

    public List<RomaneioModel> findAll() {
        return romaneioRepository.findAll();

    }

    public List<RomaneioModel> findAllByUserEmail(String userEmail) {
        return romaneioRepository.findAllByUserEmail(userEmail);
    }
}
