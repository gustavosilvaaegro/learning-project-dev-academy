package com.aegro.demo.InterfaceAdpter.Repository.Repository;

import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.Domain.IRepository.InterfaceRomaneioRepository;
import com.aegro.demo.InterfaceAdpter.Repository.Entities.Romaneio;
import com.aegro.demo.InterfaceAdpter.Repository.InterfaceRepository.RomaneioRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class RomaneioRepository implements InterfaceRomaneioRepository {

    @Autowired
    private RomaneioRepositoryMongo romaneioRepository;

    @Override
    public void save(RomaneioModel romaneioModel) {
        Romaneio romaneioEntity = new Romaneio();
        romaneioEntity = romaneioModel.toEntity();
        romaneioRepository.save(romaneioEntity);
    }

    @Override
    public Optional<Romaneio> findByUserEmail(String email) {
        return romaneioRepository.findByUserEmail(email);
    }
    @Override
    public List<Romaneio> findAllByUserEmail(String email) {
        return romaneioRepository.findAllByUserEmail(email);
    }

    @Override
    public Romaneio findById(String id) {
        return null;
    }
}
