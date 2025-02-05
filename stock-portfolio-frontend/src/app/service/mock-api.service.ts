import { Injectable } from '@angular/core';
import { error } from 'console';
import e from 'express';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MockApiService {

  constructor() { }
  register(user: any): Observable<any> {
    console.log('Mock register called with:', user);
    return of({message: 'user registered successfully', user});
  }
  login(credentials: any): Observable<any> {
    console.log('Mock login called with:', credentials);
    if (credentials.username === 'john_doe' && credentials.password === 'password123') {
      return of({ token: 'mock-jwt-token-12345'});
    } else {
      return of({error: 'Invalid credentials' });
    }}
    
    
  getPortfolio(username: string): Observable<any> {
      console.log('Mock getPortfolio called for:', username);
      return of({
        username: username,
        portfolio: [
          { stock: "AAPL", shares: 10, price: 150},
          { stock: "GOOGL", shares: 5, price: 2000},
          { stock: "TSLA", shares: 3, price: 900}
        ]
      });
    }
    getAllStocks(): Observable<any> {
      return of([
        { symbol: 'AAPL', name: 'Apple Inc.', price: 150 },
        { symbol: 'GOOGL', name: 'Alphabet Inc.', price: 2000 },
        { symbol: 'TSLA', name: 'Tesla Inc.', price: 900 }
      ]);
    }

    
  
}
