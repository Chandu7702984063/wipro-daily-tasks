import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Product } from '../models/product';

@Component({
  selector: 'app-productedit',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './productedit.component.html',
  styleUrl: './productedit.component.css'
})
export class ProductEdit implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {}

  id: string | null = null;
  product: Product = { name: '', category: '', price: 0 };

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.productService.findProduct(this.id).subscribe({
        next: (p) => this.product = p,
        error: (err) => console.error('Error loading product:', err)
      });
    }
  }

  save() {
    this.product.price = Number(this.product.price);
    this.productService.saveProduct(this.product).subscribe({
      next: () => this.router.navigate(['/productlist']),
      error: (err) => console.error('Error saving product:', err)
    });
  }
}
