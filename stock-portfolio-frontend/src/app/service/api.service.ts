import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private BASE_URL = 'http://localhost:8080/api'

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post('${this.BASE_URL}/users/register', user);
  }

  login(credentials: any):Observable<any> {
    return this.http.post('${this.BASE_URL}/auth/login', credentials);
  }
  getPortfolio(username: string): Observable<any> {
    return this.http.get('${this.BASE_URL}/stocks/all');
  }
}
