import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProductService } from '../services/product.service';
import { Product } from '../models/product';

@Component({
  selector: 'app-productlist',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './productlist.component.html',
  styleUrl: './productlist.component.css'
})
export class ProductList implements OnInit {

  constructor(private productService: ProductService, private router: Router) {}
  products: Product[] = [];

  ngOnInit() {
    this.productService.getProducts().subscribe({
      next: (data) => this.products = data,
      error: (err) => console.error('Error fetching products:', err)
    });
  }

  addProduct() {
    this.router.navigate(['/productadd']);
  }
}
