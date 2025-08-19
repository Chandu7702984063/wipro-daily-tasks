import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-fruit',
  imports: [],
  templateUrl: './fruit.component.html',
  styleUrl: './fruit.component.css'
})
export class FruitComponent {
  @Input() fruitName: string = '';

}
