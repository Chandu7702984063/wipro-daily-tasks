import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule],
  selector: 'app-product-list',
  templateUrl: './product-list.component.html'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  role: string | null = null;

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.loadProducts();
  }

  loadProducts() {
    this.productService.list().subscribe(data => this.products = data);
  }

  delete(id: number) {
    if (confirm('Delete product?')) {
      this.productService.delete(id).subscribe(() => this.loadProducts());
    }
  }

  reserve(id: number) {
    this.productService.reserve(id, 1).subscribe(() => this.loadProducts());
  }

  goToEdit(id?: number) {
    if (id) {
      this.router.navigate(['/admin/products', id]);
    } else {
      this.router.navigate(['/admin/products/new']);
    }
  }
}
