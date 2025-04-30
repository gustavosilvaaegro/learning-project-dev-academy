package com.aegro.demo.Application.Mappers;

import com.aegro.demo.Application.Dtos.RomaneioDTO;
import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;

public class RomaneioMapper {
    public static RomaneioModel toModel(RomaneioDTO dto){
        return new RomaneioModel(dto.id(), dto.userEmail(), dto.fileName(), FileInfoMapper.toModel(dto.fileInfo()));
    }
    public static RomaneioDTO toDTO(RomaneioModel model){
        return new RomaneioDTO(model.getId(), model.getUserEmail(), model.getFileName(), FileInfoMapper.toDTO(model.getFileInfoModel()));
    }
}
