import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Login } from "../models/login";


@Injectable({
    providedIn: 'root'
})
export class LoginService{
    private apiUrl = 'http://localhost:8080/auth';

    constructor(private http: HttpClient){}

    login(login: Login): Observable<Login>{
        const url = `${this.apiUrl}/login`;
        return this.http.post<Login>(url, login);
    }
}