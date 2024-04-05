import { Routes } from '@angular/router';

import { EstadoListComponent } from './estado/components/estado-list/estado-list.component';
import { EstadoFormComponent } from './estado/components/estado-form/estado-form.component';
import { estadoResolver } from './estado/resolver/estado-resolver';
import { municipioListComponent } from './municipio/components/municipio-list/municipio-list.component';
import { municipioFormComponent } from './municipio/components/municipio-form/municipio-form.component';
import { RacaoListComponent } from './racao/components/racao-list/racao-list.component';
import { RacaoFormComponent } from './racao/components/racao-form/racao-form.component';
import { municipioResolver } from './municipio/resolver/municipio-resolver';
import { PetListComponent } from './pet/components/pet-list/pet-list.component';
import { PetFormComponent } from './pet/components/pet-form/pet-form.component';
import { petResolver } from './pet/resolver/pet-resolver';
import { racaoResolver } from './racao/resolver/racao-resolver';
import { ConsultaListComponent } from './consulta/component/consulta-list/consulta-list.component';
import { ConsultaFormComponent } from './consulta/component/consulta-form/consulta-form.component';
import { ConsultaResolver } from './consulta/resolver/consulta-resolver';


export const routes: Routes = [
    { path: 'estado', component: EstadoListComponent, title: 'Lista de Estados'},
    { path: 'estado/new', component: EstadoFormComponent, title: 'Novo Estado'},
    { path: 'estado/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}},

    { path: 'municipios', component: municipioListComponent, title: 'Lista de municípios'},
    { path: 'municipios/new', component: municipioFormComponent, title: 'Novo município'},
    { path: 'municipios/edit/:id', component: municipioFormComponent, resolve: {municipio: municipioResolver}},

    { path: 'racao', component: RacaoListComponent, title: 'Lista de Rações' },
    { path: 'racao/new', component: RacaoFormComponent, title: 'Nova Ração' },
    { path: 'racao/edit/:id', component: RacaoFormComponent, resolve: { racao: racaoResolver } },

    { path: 'pets', component: PetListComponent, title: 'Lista de Pets' },
    { path: 'pets/new', component: PetFormComponent, title: 'Novo Pet' },
    { path: 'pets/edit/:id', component: PetFormComponent, resolve: { pet: petResolver } },

    { path: 'consultas', component: ConsultaListComponent, title: 'Lista de Consultas' },
    { path: 'consultas/new', component: ConsultaFormComponent, title: 'Nova Consulta' },
    { path: 'consultas/edit/:id', component: ConsultaFormComponent, resolve: { consulta: ConsultaResolver } }
];
