package com.aegro.demo.Application.Mappers;

import com.aegro.demo.Application.Dtos.FileInfoDTO;
import com.aegro.demo.Domain.EntitiesModel.FileInfoModel;

public class FileInfoMapper {
    public static FileInfoModel toModel(FileInfoDTO dto) {
        return new FileInfoModel(
                dto.data(),
                dto.descontosImpurezaQuantidadeKg(),
                dto.descontosUmidadePercentual(),
                dto.placaVeiculo(),
                dto.numeroRomaneio(),
                dto.pesoBrutoKg(),
                dto.taraKg(),
                dto.nomeMotorista(),
                dto.produto(),
                dto.produtor(),
                dto.descontosImpurezaPercentual(),
                dto.pesoLiquidoKg(),
                dto.totalDescontosKg(),
                dto.destinoArmazem(),
                dto.descontosUmidadeQuantidadeKg()
        );
    }

    public static FileInfoDTO toDTO(FileInfoModel model) {
        return new FileInfoDTO(
                model.getData(),
                model.getDescontos_impureza_quantidade_kg(),
                model.getDescontos_umidade_percentual(),
                model.getPlaca_veiculo(),
                model.getNumero_romaneio(),
                model.getPeso_bruto_kg(),
                model.getTara_kg(),
                model.getNome_motorista(),
                model.getProduto(),
                model.getProdutor(),
                model.getDescontos_impureza_percentual(),
                model.getPeso_liquido_kg(),
                model.getTotal_descontos_kg(),
                model.getDestino_armazem(),
                model.getDescontos_umidade_quantidade_kg()
        );
    }
}
