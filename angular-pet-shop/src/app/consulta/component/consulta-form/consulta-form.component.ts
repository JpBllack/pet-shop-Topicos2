import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-consulta-form',
  standalone: true,
  templateUrl: './consulta-form.component.html',
  styleUrl: './consulta-form.component.css'
})
export class ConsultaFormComponent implements OnInit {

  consulta: Consulta | undefined;

  constructor(private consultaService: ConsultaService, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    const consultaId = this.activatedRoute.snapshot.paramMap.get('id');
    if (consultaId) {
      this.consultaService.findById(consultaId).subscribe(data => {
        this.consulta = data;
      });
    }
  }
}
