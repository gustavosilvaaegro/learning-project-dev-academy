package com.aegro.demo.InterfaceAdpter.Repository.Repository;

import com.aegro.demo.Application.Dtos.FileInfoDTO;
import com.aegro.demo.Domain.EntitiesModel.FileInfoModel;
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
    public List<RomaneioModel> findAllByUserEmail(String email) {
        List<Romaneio> romaneios = romaneioRepository.findAllByUserEmail(email);
        return romaneios.stream().map(this::toModel).toList();
    }

    @Override
    public Romaneio findById(String id) {
        return null;
    }

    @Override
    public List<RomaneioModel> findAll() {
        List<Romaneio> romaneios =  romaneioRepository.findAll();
        return romaneios.stream().map(this::toModel).toList();
    }

    private RomaneioModel toModel(Romaneio romaneio) {

            FileInfoModel fileInfoModel = new FileInfoModel(
                    romaneio.getFileInfo().getData(),
                    romaneio.getFileInfo().getDescontos_impureza_quantidade_kg(),
                    romaneio.getFileInfo().getDescontos_umidade_percentual(),
                    romaneio.getFileInfo().getPlaca_veiculo(),
                    romaneio.getFileInfo().getNumero_romaneio(),
                    romaneio.getFileInfo().getPeso_bruto_kg(),
                    romaneio.getFileInfo().getTara_kg(),
                    romaneio.getFileInfo().getNome_motorista(),
                    romaneio.getFileInfo().getProduto(),
                    romaneio.getFileInfo().getProdutor(),
                    romaneio.getFileInfo().getDescontos_impureza_percentual(),
                    romaneio.getFileInfo().getPeso_liquido_kg(),
                    romaneio.getFileInfo().getTotal_descontos_kg(),
                    romaneio.getFileInfo().getDestino_armazem(),
                    romaneio.getFileInfo().getDescontos_umidade_quantidade_kg()
            );
            return new RomaneioModel(romaneio.getId(), romaneio.getUserEmail(), romaneio.getFileName(), fileInfoModel);

    }
}