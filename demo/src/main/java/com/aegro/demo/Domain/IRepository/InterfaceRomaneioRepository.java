package com.aegro.demo.Domain.IRepository;

import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.InterfaceAdpter.Repository.Entities.Romaneio;

import java.util.List;
import java.util.Optional;

public interface InterfaceRomaneioRepository {
    void save(RomaneioModel romaneioModel);
    List<RomaneioModel> findAllByUserEmail(String email);
    List<RomaneioModel> findAll();
    Romaneio findById(String id);
}
