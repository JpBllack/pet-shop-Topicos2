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
import { MatIcon } from "@angular/material/icon";
import { BasicUserService } from "../../services/basicUser.service";

@Component({
    selector: 'app-signup',
    standalone: true,
    imports: [FormsModule, HttpClientModule, NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule, MatIcon],
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.css'
})
export class SignUpComponent implements OnInit{

    senhaVisivel: boolean = false;
    formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private signupService: BasicUserService, private activatedRoute: ActivatedRoute){
        this.formGroup = formBuilder.group({
            email: ['', Validators.required],
            senha: ['', Validators.required]
        })
    }

    ngOnInit(): void {
        const signup: Login = this.activatedRoute.snapshot.data['signup'];
        if(signup){
            this.formGroup.patchValue(signup)
        }
    }

    signup(){
        if(this.formGroup.valid){
            const signup = this.formGroup.value;
                this.signupService.insert(signup).subscribe(
                    response => {
                        alert("Cadastrado com sucesso");
                        // adicionar outras ações e redirecionamento para outra página
                    },
                    error => {
                        alert("Erro ao Cadastrar");
                        // tratar o erro de login, mensagem de erro
                    }
                );
        }
        
    }

    toggleSenhaVisivel() {
        this.senhaVisivel = !this.senhaVisivel;
    }
    
}

