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
import { TipoAnimalListComponent } from './tipoAnimal/components/tipoAnimal-list/tipoAnimal-list.component';
import { tipoAnimalResolver } from './tipoAnimal/resolver/tipoAnimal-resolver';
import { TipoAnimalFormComponent } from './tipoAnimal/components/tipoAnimal-form/tipoAnimal-form.component';
import { UsuarioListComponent } from './usuario/components/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './usuario/components/usuario-form/usuario-form.component';
import { usuarioResolver } from './usuario/resolver/usuario-resolver';

export const routes: Routes = [
    { path: 'estado/all', component: EstadoListComponent, data: { title: 'Lista de Estados'} },
    { path: 'estado/new', component: EstadoFormComponent, data: { title: 'Novo Estado'} },
    { path: 'estado/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}, data: { title: 'Editar Estado'} },

    { path: 'municipios', component: municipioListComponent, data: { title: 'Lista de municípios'} },
    { path: 'municipios/new', component: municipioFormComponent, data: { title: 'Novo município'} },
    { path: 'municipios/edit/:id', component: municipioFormComponent, resolve: {municipio: municipioResolver}, data: { title: 'Editar Município'} },

    { path: 'racoes/all', component: RacaoListComponent, data: { title: 'Lista de Rações' } },
    { path: 'racoes/new', component: RacaoFormComponent, data: { title: 'Nova Ração' } },
    { path: 'racoes/edit/:id', component: RacaoFormComponent, resolve: { racao: racaoResolver }, data: { title: 'Editar Ração' } },

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
