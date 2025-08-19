import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-style-demo',
  imports: [CommonModule],
  templateUrl: './style-demo.component.html',
  styleUrl: './style-demo.component.css'
})
export class StyleDemoComponent {

  isRed = true;
  isBlue = true;
  getNgClass() {
    return {
      'red-box': this.isRed,
      'blue-box': this.isBlue
    };
  }

}
