import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { tap } from 'rxjs';
import { AuthRequest } from '../models/auth-request';
import { AuthResponse } from '../models/auth-response';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private TOKEN_KEY = 'token';

  constructor(private http: HttpClient) {}

  register(data: AuthRequest) {
    return this.http.post(`${environment.apiUrl}/auth/register`, data);
  }

  login(data: AuthRequest) {
    return this.http.post<AuthResponse>(`${environment.apiUrl}/auth/login`, data)
      .pipe(tap(res => {
        if (res.token) localStorage.setItem(this.TOKEN_KEY, res.token);
      }));
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
