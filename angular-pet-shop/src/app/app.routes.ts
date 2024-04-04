import { Routes } from '@angular/router';
 
import { EstadoListComponent } from './estado/components/estado-list/estado-list.component';
import { EstadoFormComponent } from './estado/components/estado-form/estado-form.component';
import { estadoResolver } from './estado/resolver/estado-resolver';
import { municipioListComponent } from './municipio/components/municipio-list/municipio-list.component';
import { municipioFormComponent } from './municipio/components/municipio-form/municipio-form.component';
import { RacaoListComponent } from './racao/components/racao-list/racao-list.component';
import { municipioResolver } from './municipio/resolver/municipio-resolver';
import { PetListComponent } from './pet/components/pet-list/pet-list.component';
import { PetFormComponent } from './pet/components/pet-form/pet-form.component';
import { petResolver } from './pet/resolver/pet-resolver';

export const routes: Routes = [
    { path: 'estado', component: EstadoListComponent, title: 'Lista de Estados'},
    { path: 'estado/new', component: EstadoFormComponent, title: 'Novo Estado'},
    { path: 'estado/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}},

    { path: 'municipios', component: municipioListComponent, title: 'Lista de municipios'},
    { path: 'municipios/new', component: municipioFormComponent, title: 'Novo municipio'},
    { path: 'municipios/edit/:id', component: municipioFormComponent, resolve: {municipio: municipioResolver}},

    { path: 'racao', component: RacaoListComponent, title: 'Lista de Rações' },

    { path: 'pets', component: PetListComponent, title: 'Lista de Pets'},
    { path: 'pets/new', component: PetFormComponent, title: 'Novo pet'},
    { path: 'pets/edit/:id', component: PetFormComponent, resolve: {pet: petResolver}}
];
