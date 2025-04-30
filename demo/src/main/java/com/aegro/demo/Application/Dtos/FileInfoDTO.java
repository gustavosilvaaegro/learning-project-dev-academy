package com.aegro.demo.Application.Dtos;

public record FileInfoDTO(
        String data,
        String descontosImpurezaQuantidadeKg,
        String descontosUmidadePercentual,
        String placaVeiculo,
        String numeroRomaneio,
        String pesoBrutoKg,
        String taraKg,
        String nomeMotorista,
        String produto,
        String produtor,
        String descontosImpurezaPercentual,
        String pesoLiquidoKg,
        String totalDescontosKg,
        String destinoArmazem,
        String descontosUmidadeQuantidadeKg
) {}
