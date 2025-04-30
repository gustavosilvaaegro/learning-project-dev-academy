package com.aegro.demo.InterfaceAdpter.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "romaneio")
public class Romaneio {

    @Id
    private String id;
    private String userEmail;
    private String fileName;
    private FileInfo fileInfo;

}
