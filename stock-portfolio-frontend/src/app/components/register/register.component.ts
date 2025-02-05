import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { error } from 'console';
import e, { response } from 'express';
import { ApiService } from '../../service/api.service';
import { FormsModule } from '@angular/forms';
import { MockApiService } from '../../service/mock-api.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  user  = {
    username: '',
    email: '',
    password: ''
  };
  constructor(private mockApiService: MockApiService) {}

  registerUser() {
    this.mockApiService.register(this.user).subscribe({
      next: (response) => {
        alert(response.message);
        console.log(response);
      },
      error: (error) => {
        alert('Registration failed!');
        console.error(error);
      }
    })
  }
}
