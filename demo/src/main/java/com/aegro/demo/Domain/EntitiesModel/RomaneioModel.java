package com.aegro.demo.Domain.EntitiesModel;

import com.aegro.demo.InterfaceAdpter.Repository.Entities.Romaneio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RomaneioModel {

    private String id;
    private String userEmail;
    private String fileName;
    private FileInfoModel fileInfoModel;

    public Romaneio toEntity() {
        return new Romaneio(
                this.id,
                this.userEmail,
                this.fileName,
                this.fileInfoModel.toEntity()
        );
    }
}
