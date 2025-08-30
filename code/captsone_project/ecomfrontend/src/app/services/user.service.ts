import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  me(): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/user/me`);
  }

  update(user: User): Observable<User> {
    return this.http.put<User>(`${environment.apiUrl}/user`, user);
  }
}



