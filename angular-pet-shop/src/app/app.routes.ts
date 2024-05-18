import { Routes } from '@angular/router';

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
import { municipioListComponent } from './component/municipio/components/municipio-list/municipio-list.component';
import { MarcaListComponent } from './component/marca/component/marca-list/marca-list.component';
import { MarcaFormComponent } from './component/marca/component/marca-form/marca-form.component';
import { marcaResolver } from './component/marca/resolver/marca-resolver';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserTemplateComponent } from './pages/template/user-template/user-template.component';
import { RacaoCardListComponent } from './component/racao-card-list/racao-card-list.component';
import { CarrinhoComponent } from './pages/carrinho/carrinho.component';
import { AdminTemplateComponent } from './pages/template/admin-template/admin-template.component';

export const routes: Routes = [

    { 
        path: '', 
        component: UserTemplateComponent, 
        title: 'e-commerce',
        children: [
            {path: '', pathMatch: 'full', redirectTo: 'produtos'},

            { path: 'produtos', component: RacaoCardListComponent, title: 'Produtos à Venda'},
            { path: 'login', component: LoginComponent, title: 'Login'},
            { path: 'carrinho', component: CarrinhoComponent, title: 'Carrinho de pedidos'},
            { path: 'signup', component: SignupComponent, data: {Title: 'Sign Up'}},
            { path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard'}},
        ]

    },

    { path: 'admin', 
  component: AdminTemplateComponent, 
  title: 'e-commerce',
  children: [
      {path: '', pathMatch: 'full', redirectTo: 'estados'},
      
      { path: 'estados', component: EstadoListComponent, data: { title: 'Lista de Estados'} },
      { path: 'estados/new', component: EstadoFormComponent, data: { title: 'Novo Estado'} },
      { path: 'estados/edit/:id', component: EstadoFormComponent, resolve: {estado: estadoResolver}, data: { title: 'Editar Estado'} },
          
      { path: 'municipios', component: municipioListComponent, data: { title: 'Lista de municípios'} },
      { path: 'municipios/new', component: MunicipioFormComponent, data: { title: 'Novo município'} },
      { path: 'municipios/edit/:id', component: MunicipioFormComponent, resolve: {municipio: municipioResolver}, data: { title: 'Editar Município'} },
          
      { path: 'racoes', component: RacaoListComponent, data: { title: 'Lista de Rações' } },
      { path: 'racoes/new', component: RacaoFormComponent, data: { title: 'Nova Ração' } },
      { path: 'racoes/edit/:id', component: RacaoFormComponent, resolve: { racao: racaoResolver }, data: { title: 'Editar Ração' } },

      { path: 'pets', component: PetListComponent, data: { title: 'Lista de Pets' } },
      { path: 'pets/new', component: PetFormComponent, data: { title: 'Novo Pet' } },
      { path: 'pets/edit/:id', component: PetFormComponent, resolve: { pet: petResolver }, data: { title: 'Editar Pet' } },

      { path: 'consultas', component: ConsultaListComponent, data: { title: 'Lista de Consultas' } },
      { path: 'consultas/new', component: ConsultaFormComponent, data: { title: 'Nova Consulta' } },
      { path: 'consultas/edit/:id', component: ConsultaFormComponent, resolve: { consulta: ConsultaResolver }, data: { title: 'Editar Consulta' } },
          
      { path: 'tipos', component: TipoAnimalListComponent, data: { title: 'Lista de tipos de animal'} },
      { path: 'tipos/new', component: TipoAnimalFormComponent, data: { title: 'Novo tipo animal'} },
      { path: 'tipos/edit/:id', component: TipoAnimalFormComponent, resolve: {tipoAnimal: tipoAnimalResolver}, data: { title: 'Editar Tipo de Animal'} },

      { path: 'usuarios/all', component: UsuarioListComponent, data: { title: "Lista de usuarios"} },
      { path: 'usuarios/new', component: UsuarioFormComponent, data: { title: 'Novo usuario'} },
      { path: 'usuarios/edit/:id', component: UsuarioFormComponent, resolve: {usuario: usuarioResolver}, data: { title: 'Editar Usuario'} },

      { path: 'marcas', component: MarcaListComponent, data: { title: 'Lista de marcas'} },
      { path: 'marcas/new', component: MarcaFormComponent, data: { title: 'Nova marca'} },
      { path: 'marcas/edit/:id', component: MarcaFormComponent, resolve: {marca: marcaResolver}, data: { title: 'Editar marca'} },
  ]
}


];
