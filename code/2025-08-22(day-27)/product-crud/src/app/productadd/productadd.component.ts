import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-productadd',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './productadd.component.html',
  styleUrl: './productadd.component.css'
})
export class ProductAdd {
  constructor(private productService: ProductService, private router: Router) {}

  name = '';
  category = '';
  price: number | null = null;
  qty: number | null = null;   // 👈 Added qty field

  submit() {
    if (!this.name || !this.category || this.price === null || this.qty === null) return;

    this.productService.addProduct({
      name: this.name,
      category: this.category,
      price: Number(this.price),
      qty: Number(this.qty)  // 👈 include qty
    }).subscribe({
      next: () => {
        this.name = '';
        this.category = '';
        this.price = null;
        this.qty = null;
        this.router.navigate(['/productlist']);
      },
      error: (err) => console.error('Error adding product:', err)
    });
  }
}

