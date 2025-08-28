import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';          // ✅ Add
import { HttpClientModule } from '@angular/common/http'; // ✅ Add
import { Router } from '@angular/router';               // ✅ Add

@Component({
  selector: 'app-root',
  standalone: true,             // ✅ Ensure standalone true
  imports: [RouterOutlet, FormsModule, HttpClientModule], // ✅ Add Forms & HttpClient
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent {
  constructor(private router: Router) {}

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}