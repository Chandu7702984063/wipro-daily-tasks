import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { MessageToggleComponent } from './message-toggle/message-toggle.component';

import { HomeComponent } from './home/home.component';
@Component({
  selector: 'app-root',
  standalone: true,   
  imports: [FormsModule, CommonModule,HomeComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']  
})
export class AppComponent {
  // countries: string[] = ["India", "USA", "Canada", "Australia", "Germany", "France"];
  // dropdownCountries: string[] = []; 

  // loadCountries() {
  //   this.dropdownCountries = this.countries;
  // }
  // color: string = 'black';

  searchText: string = '';

  names: string[] = [
    'Jayanta',
    'Jayaram',
    'John',
    'Ravi',
    'Ramesh',
    'Suresh'
  ];

  get filteredNames(): string[] {
    return this.names.filter(name =>
      name.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

}
