import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';   // Import FormsModule
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [FormsModule, CommonModule]   // Required for ngModel and *ngIf/*ngFor
})
export class RegisterComponent {
  username = '';
  password = '';
  message = '';

  constructor(private auth: AuthService, private router: Router) {}

  onRegister() {
    this.auth.register(this.username, this.password).subscribe({
      next: () => {
        this.message = '✅ User registered successfully!';
        // Redirect to login page after successful registration
        this.router.navigate(['/login']);
      },
      error: () => {
        this.message = '❌ Registration failed. Try again.';
      }
    });
  }
}
