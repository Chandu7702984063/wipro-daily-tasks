import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8083/auth'; 

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { username, password });
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, { username, password });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }
}
