import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) { }

  getUser(username: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}?username=${username}`)
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('SESSID') != null
  }
}
