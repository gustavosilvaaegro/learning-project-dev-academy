package com.aegro.demo.Domain.EntitiesModel;

import com.aegro.demo.InterfaceAdpter.Entities.Romaneio;
import com.aegro.demo.InterfaceAdpter.Entities.FileInfo;
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
    private FileInfoModel fileInfo;

    public Romaneio toEntity() {
        return new Romaneio(
                this.id,
                this.userEmail,
                this.fileName,
                this.fileInfo.toEntity()
        );
    }
}
