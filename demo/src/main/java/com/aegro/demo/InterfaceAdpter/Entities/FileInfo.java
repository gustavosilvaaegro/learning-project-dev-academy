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
    private String descontos_impureza_quantidade_kg;
    private String descontos_umidade_percentual;
    private String placa_veiculo;
    private String numero_romaneio;
    private String peso_bruto_kg;
    private String tara_kg;
    private String nome_motorista;
    private String produto;
    private String produtor;
    private String descontos_impureza_percentual;
    private String peso_liquido_kg;
    private String total_descontos_kg;
    private String destino_armazem;
    private String descontos_umidade_quantidade_kg;



}
