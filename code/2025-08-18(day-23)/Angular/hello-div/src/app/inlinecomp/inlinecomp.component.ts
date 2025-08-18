import { Component } from '@angular/core';

@Component({
  selector: 'app-inlinecomp',
  imports: [],
  template: `
    <div style="text-align:center; padding:20px; background-color:lightcoral; color:white; border-radius:10px;">
      <h2>Hello from Inline Component!</h2>
      <p>This component uses inline template instead of templateUrl.</p>
    </div>
  `,
  styles: [`
    h2 {
      font-family: Arial, sans-serif;
    }
  `]
  // styleUrl: './inlinecomp.component.css'
})
export class InlinecompComponent {

}
