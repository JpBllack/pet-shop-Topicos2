import { HttpClient, HttpClientModule } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
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

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule, HttpClientModule, NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

    formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private loginService: LoginService, private activatedRoute: ActivatedRoute){
        this.formGroup = formBuilder.group({
            email: ['', Validators.required],
            senha: ['', Validators.required]
        })
    }

    ngOnInit(): void {
        const login: Login = this.activatedRoute.snapshot.data['login'];
        if(login){
            this.formGroup.patchValue(login)
        }
    }

    login(){
        if(this.formGroup.valid){
            const login = this.formGroup.value;
                this.loginService.login(login).subscribe(
                    response => {
                        alert("Login bem sucedido");
                        // Você pode adicionar outras ações aqui, como redirecionar para outra página
                    },
                    error => {
                        alert("Erro ao logar");
                        // Aqui você pode tratar o erro de login, por exemplo, exibir uma mensagem de erro para o usuário
                    }
                );
        }
        
    }
    
}

