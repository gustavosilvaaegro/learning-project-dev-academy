package com.aegro.demo.repository;

import com.aegro.demo.model.Romaneio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RomaneioRepository extends MongoRepository<Romaneio, String> {



}
