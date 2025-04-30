package com.aegro.demo.InterfaceAdpter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfo {

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



}
