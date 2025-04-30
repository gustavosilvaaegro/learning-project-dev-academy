package com.aegro.demo.Domain.EntitiesModel;

import com.aegro.demo.InterfaceAdpter.Entities.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoModel {
    private String data;
    private String descontosImpurezaQuantidadeKg;
    private String descontosUmidadePercentual;
    private String placaVeiculo;
    private String numeroRomaneio;
    private String pesoBrutoKg;
    private String taraKg;
    private String nomeMotorista;
    private String produto;
    private String produtor;
    private String descontosImpurezaPercentual;
    private String pesoLiquidoKg;
    private String totalDescontosKg;
    private String destinoArmazem;
    private String descontosUmidadeQuantidadeKg;


    public FileInfo toEntity() {
        return new FileInfo(
                this.data,
                this.descontosImpurezaQuantidadeKg,
                this.descontosUmidadePercentual,
                this.placaVeiculo,
                this.numeroRomaneio,
                this.pesoBrutoKg,
                this.taraKg,
                this.nomeMotorista,
                this.produto,
                this.produtor,
                this.descontosImpurezaPercentual,
                this.pesoLiquidoKg,
                this.totalDescontosKg,
                this.destinoArmazem,
                this.descontosUmidadeQuantidadeKg
        );
    }
}
