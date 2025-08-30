import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService) {}

  submit() {
    this.auth.login({username: this.username, password: this.password})
      .subscribe({
        next: () => this.error = 'Login successful!',
        error: () => this.error = 'Invalid username or password'
      });
  }
}
