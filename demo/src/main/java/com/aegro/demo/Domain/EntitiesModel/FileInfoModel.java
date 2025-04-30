package com.aegro.demo.Domain.EntitiesModel;

import com.aegro.demo.InterfaceAdpter.Entities.FileInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfoModel {
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



    public FileInfo toEntity() {
        return new FileInfo(
                this.data,
                this.descontos_impureza_quantidade_kg,
                this.descontos_umidade_percentual,
                this.placa_veiculo,
                this.numero_romaneio,
                this.peso_bruto_kg,
                this.tara_kg,
                this.nome_motorista,
                this.produto,
                this.produtor,
                this.descontos_impureza_percentual,
                this.peso_liquido_kg,
                this.total_descontos_kg,
                this.destino_armazem,
                this.descontos_umidade_quantidade_kg
        );
    }
}
