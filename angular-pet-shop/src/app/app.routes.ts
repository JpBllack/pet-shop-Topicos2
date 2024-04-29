import { Routes } from '@angular/router';


import { Estado } from './models/estado.model';
import { Title } from '@angular/platform-browser';
import { ConsultaFormComponent } from './component/consulta/component/consulta-form/consulta-form.component';
import { ConsultaListComponent } from './component/consulta/component/consulta-list/consulta-list.component';
import { ConsultaResolver } from './component/consulta/resolver/consulta-resolver';
import { EstadoFormComponent } from './component/estado/components/estado-form/estado-form.component';
import { EstadoListComponent } from './component/estado/components/estado-list/estado-list.component';
import { estadoResolver } from './component/estado/resolver/estado-resolver';

import { municipioResolver } from './component/municipio/resolver/municipio-resolver';
import { PetFormComponent } from './component/pet/components/pet-form/pet-form.component';
import { PetListComponent } from './component/pet/components/pet-list/pet-list.component';
import { petResolver } from './component/pet/resolver/pet-resolver';
import { RacaoFormComponent } from './component/racao/components/racao-form/racao-form.component';
import { RacaoListComponent } from './component/racao/components/racao-list/racao-list.component';
import { racaoResolver } from './component/racao/resolver/racao-resolver';
import { TipoAnimalFormComponent } from './component/tipoAnimal/components/tipoAnimal-form/tipoAnimal-form.component';
import { TipoAnimalListComponent } from './component/tipoAnimal/components/tipoAnimal-list/tipoAnimal-list.component';
import { tipoAnimalResolver } from './component/tipoAnimal/resolver/tipoAnimal-resolver';
import { UsuarioFormComponent } from './component/usuario/components/usuario-form/usuario-form.component';
import { UsuarioListComponent } from './component/usuario/components/usuario-list/usuario-list.component';
import { usuarioResolver } from './component/usuario/resolver/usuario-resolver';
import { MunicipioFormComponent } from './component/municipio/components/municipio-form/municipio-form.component';

export const routes: Routes = [
    { path: 'estado/all', component: EstadoListComponent, data: { title: 'Lista de Estados'} },
    { path: 'estado/new', component: EstadoFormComponent, data: { title: 'Novo Estado'} },
    { path: 'estado/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}, data: { title: 'Editar Estado'} },
    { path: 'estado/delete/:id', component: EstadoFormComponent,  resolve: {estado: estadoResolver}, data:{title: 'Deletar Estado'}},

    
    { path: 'municipios/all', component: MunicipioFormComponent, data: { title: 'Lista de municípios'} },
    { path: 'municipios/new', component: MunicipioFormComponent, data: { title: 'Novo município'} },
    { path: 'municipios/edit/:id', component: MunicipioFormComponent, resolve: {municipio: municipioResolver}, data: { title: 'Editar Município'} },
    { path: 'municipios/delete/:id', component: MunicipioFormComponent, resolve: {municipio: municipioResolver}, data: { title: 'Deletar Município'} },


    { path: 'racoes/all', component: RacaoListComponent, data: { title: 'Lista de Rações' } },
    { path: 'racoes/new', component: RacaoFormComponent, data: { title: 'Nova Ração' } },
    { path: 'racoes/edit/:id', component: RacaoFormComponent, resolve: { racao: racaoResolver }, data: { title: 'Editar Ração' } },
    { path: 'racoes/delete/:id', component: RacaoFormComponent, resolve: { racao: racaoResolver }, data: { title: 'Deletar Ração' } },

    { path: 'pets/all', component: PetListComponent, data: { title: 'Lista de Pets' } },
    { path: 'pets/new', component: PetFormComponent, data: { title: 'Novo Pet' } },
    { path: 'pets/edit/:id', component: PetFormComponent, resolve: { pet: petResolver }, data: { title: 'Editar Pet' } },

    { path: 'consultas/all', component: ConsultaListComponent, data: { title: 'Lista de Consultas' } },
    { path: 'consultas/new', component: ConsultaFormComponent, data: { title: 'Nova Consulta' } },
    { path: 'consultas/edit/:id', component: ConsultaFormComponent, resolve: { consulta: ConsultaResolver }, data: { title: 'Editar Consulta' } },

    { path: 'tipos/all', component: TipoAnimalListComponent, data: { title: 'Lista de tipos de animal'} },
    { path: 'tipos/new', component: TipoAnimalFormComponent, data: { title: 'Novo tipo animal'} },
    { path: 'tipos/edit/:id', component: TipoAnimalFormComponent, resolve: {tipoAnimal: tipoAnimalResolver}, data: { title: 'Editar Tipo de Animal'} },
    { path: 'tipos/delete/:id', component: TipoAnimalFormComponent, resolve: {tipoAnimal: tipoAnimalResolver}, data: { title: 'Deletar Tipo de Animal'} },

    { path: 'usuarios/all', component: UsuarioListComponent, data: { title: "Lista de usuarios"} },
    { path: 'usuarios/new', component: UsuarioFormComponent, data: { title: 'Novo usuario'} },
    { path: 'usuarios/edit/:id', component: UsuarioFormComponent, resolve: {usuario: usuarioResolver}, data: { title: 'Editar Usuario'} },
    { path: 'usuarios/delete/:id', component: UsuarioFormComponent, resolve: {usuario: usuarioResolver}, data: { title: 'Deleter Usuario'} },
    

];
