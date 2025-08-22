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

  submit() {
    if (!this.name || !this.category || this.price === null) return;

    this.productService.addProduct({
      name: this.name,
      category: this.category,
      price: Number(this.price)
    }).subscribe({
      next: () => {
        this.name = '';
        this.category = '';
        this.price = null;
        this.router.navigate(['/productlist']);
      },
      error: (err) => console.error('Error adding product:', err)
    });
  }
}
