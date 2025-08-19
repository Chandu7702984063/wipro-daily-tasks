import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DisplayListComponent } from './display-list/display-list.component';
import { CommonModule } from '@angular/common';
import { StyleDemoComponent } from './style-demo/style-demo.component';
import { FruitComponent } from './fruit/fruit.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,HomeComponent,DisplayListComponent,CommonModule,StyleDemoComponent,FruitComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ex1';
}
