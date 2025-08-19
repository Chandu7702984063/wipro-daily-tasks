import { Component } from '@angular/core';
import { DisplayListComponent } from '../display-list/display-list.component';
import { FruitComponent } from '../fruit/fruit.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [DisplayListComponent,FruitComponent,CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  fruits: string[] = ['Apple', 'Banana', 'Orange', 'Mango', 'Grapes'];

}
