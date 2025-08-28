import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  isPalindrome(str: string): boolean {
    if (!str) return false;
    let reversed = str.split('').reverse().join('');
    return str === reversed;
  }
}
