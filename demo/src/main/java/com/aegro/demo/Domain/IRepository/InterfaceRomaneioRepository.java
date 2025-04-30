package com.aegro.demo.Domain.IRepository;

import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.InterfaceAdpter.Entities.Romaneio;

import java.util.List;
import java.util.Optional;

public interface InterfaceRomaneioRepository {
    void save(RomaneioModel romaneioModel);
    Optional<Romaneio> findByUserEmail(String email);
    List<Romaneio> findAllByUserEmail(String email);
    Romaneio findById(String id);
}
