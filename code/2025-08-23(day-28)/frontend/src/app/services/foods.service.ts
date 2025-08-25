import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface FoodItem {
  id?: number;
  name: string;
  description: string;
  price: number;
  imageUrl?: string;
}

@Injectable({
  providedIn: 'root'
})
export class FoodsService {
  private apiUrl = 'http://localhost:8083/food'; // adjust backend port

  constructor(private http: HttpClient) { }

  getAll(): Observable<FoodItem[]> {
    return this.http.get<FoodItem[]>(this.apiUrl);
  }

  getById(id: number): Observable<FoodItem> {
    return this.http.get<FoodItem>(`${this.apiUrl}/${id}`);
  }

  add(food: FoodItem): Observable<FoodItem> {
    return this.http.post<FoodItem>(this.apiUrl, food);
  }

  update(id: number, food: FoodItem): Observable<FoodItem> {
    return this.http.put<FoodItem>(`${this.apiUrl}/${id}`, food);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
