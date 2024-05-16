import { NgIf, CommonModule } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatToolbarModule } from "@angular/material/toolbar";
import { Router, RouterModule } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { BasicUserService } from "../../services/basicUser.service";
import { Login } from "../../models/login";

@Component({
    selector: 'app-signup',
    standalone: true,
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.css'],
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule, MatIconModule],
})
export class SignupComponent implements OnInit{
    senhaVisivel: boolean = false;
    loginForm!: FormGroup;

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private authService: AuthService, private signup: BasicUserService, private snackBar: MatSnackBar) {}

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
          email: ['', [Validators.required, Validators.email]],
          senha: ['', [Validators.required, Validators.minLength(3)]]
        });
    }

    onRegister() {
        if (this.loginForm.valid) {
          const loginData: Login = {
            email: this.loginForm.get('email')!.value,
            senha: this.loginForm.get('senha')!.value
          };
    
          this.signup.insert(loginData).subscribe({
            next: (resp) => {
                this.authService.login(loginData);
              // redirecionar para a página principal
              this.router.navigateByUrl('/dashboard');
              alert("Cadastrado com sucesso!")
            },
            error: (err) => {
              console.log(err);
              this.showSnackbarTopPosition("Usuário ou senha Inválidos", 'Fechar', 2000);
            }
          });
        } else {
          this.showSnackbarTopPosition("Dados inválidos", 'Fechar', 2000);
        }
    }

    showSnackbarTopPosition(content: any, action: any, duration: any) {
        this.snackBar.open(content, action, {
          duration: 2000,
          verticalPosition: "top", // Allowed values are  'top' | 'bottom'
          horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
        });
    }
    
    toggleSenhaVisivel() {
        this.senhaVisivel = !this.senhaVisivel;
    }
}
    
