import { CommonModule } from '@angular/common';
import { HttpClientJsonpModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import * as XLSX from 'xlsx';

interface TableData {
  [key: string]: string;
}

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule, HttpClientJsonpModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front';

  email: string = '';
  selectedImage: string | null = null;
  tableData: TableData[] = [];
  tableHeaders: string[] = [];
  imageFile: File | null = null;
  loading: boolean = false;

  onDragOver(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    
    const files = event.dataTransfer?.files;
    if (files && files.length > 0) {
      this.handleImageFile(files[0]);
    }
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.handleImageFile(input.files[0]);
    }
  }

  handleImageFile(file: File) {
    if (file.type.startsWith('image/')) {
      this.imageFile = file;
      const reader = new FileReader();
      reader.onload = (e) => {
        this.selectedImage = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  async uploadImage() {
    const fileInput = document.getElementById('imageInput');
    const userEmailInput = document.getElementById('userEmail');
    const resultsDiv = document.getElementById('results');
    const files = (fileInput as HTMLInputElement).files;
    const userEmail = (userEmailInput as HTMLInputElement).value;
    if (!files || files.length === 0) {
        return;
    } 
    if (!userEmail) {
        alert('Por favor, insira seu e-mail.');
        return;
    }

    this.loading = true;
    const formData = this.createFormData(files, userEmail);

    try {
        const response = await fetch('http://localhost:8080/api/images/upload', {
            method: 'POST',
            body: formData,
        });
        if (response.ok) {
          const data = await response.json();
          this.createTable(data);
          console.log(data);
          this.missingFiles(data, files);
          this.tableData
        }
    } catch (error) {
      console.error('Erro ao fazer o upload da imagem:', error);
    }
    this.loading = false;
  }

  async getEmailData(){
    const userEmailInput = document.getElementById('userEmail');
    const userEmail = (userEmailInput as HTMLInputElement).value;
    if (!userEmail) {
        alert('Por favor, insira seu e-mail.');
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/api/images/files/email?userEmail=${userEmail}`, {
            method: 'GET',
        });
        if (response.ok) {
          const data = await response.json();
          this.createTable(data);
          console.log(data);
          this.tableData
        }
        
    } catch (error) {
      console.error('Erro ao solicitar dados', error);
        
    }
    this.loading = false;
  }
  private createFormData(files: FileList, userEmail: string): FormData {
    const formData = new FormData();
    Array.from(files).forEach(file => formData.append('files', file));
    formData.append('userEmail', userEmail);
    return formData;
  }

  private createTable(data:any[]){
    this.tableHeaders = ['Nome do arquivo', 'data', 'Descontos Impureza %', 'Descontos Impureza kg', 'Peso Bruto kg', 'Peso Liquido kg', 'Nome Motorista', 'Placa Veículo', 'Desconto Umidade %', 'Desconto Umidade kg', 'Destino Armazem', 'Numero Romaneio', 'Produto', 'Produtor', 'Tara Kg', 'Total descontos kg'];
    if (Array.isArray(data)) {
      this.tableData = [];
        data.forEach((result, index) => {
            this.tableData[index] = {
                'Nome do arquivo': result.fileName,
                data: result.fileInfo.data,
                'Descontos Impureza %': result.fileInfo.descontosImpurezaPercentual,
                'Descontos Impureza kg': result.fileInfo.descontosImpurezaQuantidadeKg,
                'Peso Bruto kg': result.fileInfo.pesoBrutoKg,
                'Peso Liquido kg': result.fileInfo.pesoLiquidoKg,
                'Nome Motorista': result.fileInfo.nomeMotorista,
                'Placa Veículo': result.fileInfo.placaVeiculo,
                'Desconto Umidade %': result.fileInfo.descontosUmidadePercentual,
                'Desconto Umidade kg': result.fileInfo.descontosUmidadeQuantidadeKg,
                'Destino Armazem': result.fileInfo.destinoArmazem,
                'Numero Romaneio': result.fileInfo.numeroRomaneio,
                'Produto': result.fileInfo.produto,
                'Produtor': result.fileInfo.produtor,
                'Tara Kg': result.fileInfo.taraKg,
                'Total descontos kg': result.fileInfo.totalDescontosKg,
              }
                
            
      });
    }
  }

  private missingFiles(data: any[], files: FileList) {
    let isPresent;
    let filesNotPresent: String[] = [];
    let msg = `Erro na leitura de ${files.length - data.length} arquivos:`;

    for (let i =0; i<files.length; i++) {

        isPresent = false;

        data.forEach(data => {
            if (files[i].name === data.fileName) {
                isPresent = true;
            }
        });

        if (isPresent ===false) {
            filesNotPresent.push(files[i].name);
        }
    }
    if (filesNotPresent.length === 0) {
        return;
    }
    for (let i = 0; i < filesNotPresent.length; i++) {
        msg += `\n-${filesNotPresent[i]}`;
    }
    alert(msg);
  }


  exportToExcel() {
    const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.tableData);
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Data');
    XLSX.writeFile(wb, 'exported-data.xlsx');
  }
}
