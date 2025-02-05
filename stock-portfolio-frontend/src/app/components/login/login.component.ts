import { Component } from '@angular/core';
import { MockApiService } from '../../service/mock-api.service';
import { response } from 'express';
import { error } from 'console';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  credentials = {
    username: '',
    password: ''
  };
  constructor(private mockApiService: MockApiService) {}
  loginUser() {
    this.mockApiService.login(this.credentials).subscribe({
      next: (response) => {
        if (response.token) {
          alert('Login Successful! Token: ' + response.token);
        } else {
          alert('Login Failed: ' + response.token);
        }
      },
      error: (error) => {
        alert('Login error!');
        console.error(error);
      }
    });
  }

}
