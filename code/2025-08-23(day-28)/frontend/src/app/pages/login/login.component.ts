import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, CommonModule]   // ✅ Add FormsModule & CommonModule here
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  onLogin() {
    this.auth.login(this.username, this.password).subscribe({
      next: (res) => {
        this.auth.saveToken(res.token);
        this.router.navigate(['/foods']); // redirect after login
      },
      error: () => {
        this.error = 'Invalid credentials';
      }
    });
  }
}
