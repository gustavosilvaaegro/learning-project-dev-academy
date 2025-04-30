package com.aegro.demo.InterfaceAdpter.Repository.InterfaceRepository;

import com.aegro.demo.InterfaceAdpter.Repository.Entities.Romaneio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RomaneioRepositoryMongo extends MongoRepository<Romaneio, String> {

    Optional<Romaneio> findByUserEmail(String email);
    List<Romaneio> findAllByUserEmail(String email);
}
