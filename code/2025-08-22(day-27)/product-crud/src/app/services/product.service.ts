import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { Order } from '../models/order';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private apiUrl = 'http://localhost:3000/products';
   private orderUrl = 'http://localhost:3000/orders';

  constructor(private http: HttpClient) {}

  
  addProduct(p: Omit<Product, 'id'> & { id?: string }): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, { ...p, price: Number(p.price) });
  }
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  findProduct(id: string): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }
  saveProduct(p: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/${p.id}`, { ...p, price: Number(p.price) });
  }
  deleteProduct(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  placeOrder(productId: number, qty: number): Observable<Order> {
    return this.http.post<Order>(`${this.orderUrl}/place/${productId}?qty=${qty}`, {});
  }

  getOrderHistory(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.orderUrl}/history`);
  }

}
