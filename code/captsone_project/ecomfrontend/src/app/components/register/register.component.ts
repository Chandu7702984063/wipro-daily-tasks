import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
   standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  username = '';
  password = '';
  role = 'CUSTOMER';
  message = '';

  constructor(private auth: AuthService) {}

  submit() {
    this.auth.register({username: this.username, password: this.password, role: this.role})
      .subscribe({
        next: () => this.message = 'Registered successfully!',
        error: () => this.message = 'Failed to register'
      });
  }
}
