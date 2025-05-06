package com.aegro.demo.Application.UseCases;

import com.aegro.demo.Application.Dtos.RomaneioDTO;
import com.aegro.demo.Application.Mappers.RomaneioMapper;
import com.aegro.demo.Domain.service.RomaneioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RomaneioUC {

    @Autowired
    private RomaneioService romaneioService;

    public List<RomaneioDTO> findAll() {
        return romaneioService.findAll()
                .stream()
                .map(RomaneioMapper::toDTO)
                .toList();
    }

    public List<RomaneioDTO> findAllByUserEmail(String userEmail) {
        return romaneioService.findAllByUserEmail(userEmail)
                .stream()
                .map(RomaneioMapper::toDTO)
                .toList();
    }
}
