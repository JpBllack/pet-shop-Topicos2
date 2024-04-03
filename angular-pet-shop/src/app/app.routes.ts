import { Routes } from '@angular/router';
import { EstadoListComponent } from './components/estado/estado-list/estado-list.component';
import { EstadoFormComponent } from './components/estado/estado-form/estado-form.component';
import { estadoResolver } from './components/estado/resolver/estado-resolver';
import { municipioFormComponent } from './components/municipio/municipio-form/municipio-form.component';
import { municipioResolver } from './components/municipio/resolver/municipio-resolver';
import { municipioListComponent } from './components/municipio/municipio-list/municipio-list.component';
import { RacaoListComponent } from './components/racao/racao-list/racao-list.component'; // Importe o componente de ração aqui

export const routes: Routes = [
    { path: 'estado', component: EstadoListComponent, title: 'Lista de Estados'},
    { path: 'estado/new', component: EstadoFormComponent, title: 'Novo Estado'},
    { path: 'estado/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}},

    { path: 'municipios', component: municipioListComponent, title: 'Lista de municipios'},
    { path: 'municipios/new', component: municipioFormComponent, title: 'Novo municipio'},
    { path: 'municipios/edit/:id', component: municipioFormComponent, resolve: {municipio: municipioResolver}},

    { path: 'racao', component: RacaoListComponent, title: 'Lista de Rações' },
];
