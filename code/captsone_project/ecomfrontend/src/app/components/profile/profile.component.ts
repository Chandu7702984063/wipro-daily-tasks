import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';      // ✅ import FormsModule
import { NgIf } from '@angular/common';            // ✅ needed for *ngIf
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FormsModule, NgIf],    // ✅ include here
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  user?: User;
  message = '';
  error = '';

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadProfile();
  }

  loadProfile() {
    this.userService.me().subscribe({
      next: (u) => this.user = u,
      error: () => this.error = 'Failed to load profile'
    });
  }

  save() {
    if (!this.user) return;
    this.userService.update(this.user).subscribe({
      next: (updated) => {
        this.user = updated;
        this.message = 'Profile updated successfully!';
      },
      error: () => this.error = 'Profile update failed'
    });
  }
}
