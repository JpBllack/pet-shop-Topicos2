import { HttpClient, HttpClientModule } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";

import { Login } from "../../models/login";
import { LoginService } from "../../services/login.service";
import { NgIf, CommonModule } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIcon } from "@angular/material/icon";
import { MatSnackBar } from '@angular/material/snack-bar';

import { JwtModule, JwtHelperService } from '@auth0/angular-jwt';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LocalStorageService } from "../../services/localStorage.service";

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, FormsModule, HttpClientModule, NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule, MatIcon, JwtHelperService],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
    providers: [JwtHelperService]
})
export class LoginComponent implements OnInit{

    senhaVisivel: boolean = false;
    loginForm: FormGroup;

    constructor(private formBuilder: FormBuilder, 
      private http: HttpClient, 
      private router: Router, 
      private loginService: LoginService, 
      private activatedRoute: ActivatedRoute, 
      private localStorageService: LocalStorageService, 
      private snackBar: MatSnackBar){
        this.loginForm = formBuilder.group({
            email: ['', Validators.required],
            senha: ['', Validators.required]
        })
    }

    ngOnInit(): void {
        const login: Login = this.activatedRoute.snapshot.data['login'];
        if(login){
            this.loginForm.patchValue(login)
        }
    }

    login() {
        if (this.loginForm.valid) {
          const login = this.loginForm.value;
          this.loginService.login(login).subscribe(
            {
              next: (resp) => {

                if (resp.body.perfil == "ADMIN") {
      
                  this.router.navigateByUrl('/admin');
                }
      
                else {
                  //this.showSnackbarTopPosition(this.authService.getToken(), 'Fechar', 2000);
                  // redirecionar para a página principal
                  this.router.navigateByUrl('/user/compras/produtos');
                }
              },
              error: (err) => {
                console.log(err);
                this.showSnackbarTopPosition("Usuário ou senha Inválidos ⚠️", 'Fechar', 2000);
              }
            }
          );
        } else {
          this.showSnackbarTopPosition("Dados inválidos ⛔", 'Fechar', 2000);
        }
      }
    
      

    toggleSenhaVisivel() {
        this.senhaVisivel = !this.senhaVisivel;
    }

    showSnackbarTopPosition(content:any, action:any, duration:any) {
      this.snackBar.open(content, action, {
        duration: 2000,
        verticalPosition: "top", // Allowed values are  'top' | 'bottom'
        horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
      });
    }
    
}

