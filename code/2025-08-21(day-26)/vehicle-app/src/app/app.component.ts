import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { VehicleCompComponent } from './vehicle-comp/vehicle-comp.component';

@Component({
  selector: 'app-root',
  standalone: true,   // 👈 mark as standalone
  imports: [
    RouterOutlet,
    HttpClientModule,
    VehicleCompComponent   // 👈 import your Vehicle component here
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'vehicle-app';
}
