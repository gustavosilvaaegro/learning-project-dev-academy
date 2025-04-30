package com.aegro.demo.InterfaceAdpter.Repository;

import com.aegro.demo.InterfaceAdpter.Entities.Romaneio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RomaneioRepository extends MongoRepository<Romaneio, String> {



}
