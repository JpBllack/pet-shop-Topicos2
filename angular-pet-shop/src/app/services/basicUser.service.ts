import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Login } from "../models/login";

@Injectable({
    providedIn: 'root'
})
export class BasicUserService{
    private apiUrl = 'http://localhost:8080/basicUsers';

    constructor(private http: HttpClient) {}

    insert(login: Login): Observable<Login>{
        const url = `${this.apiUrl}/insert`;
        return this.http.post<Login>(url, login);
    }
}