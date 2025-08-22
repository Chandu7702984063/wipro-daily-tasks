import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-productdelete',
  standalone: true,
  imports: [],
  templateUrl: './productdelete.component.html',
  styleUrl: './productdelete.component.css'
})
export class ProductDelete implements OnInit {

  id: string | null = null;

  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.productService.deleteProduct(this.id).subscribe({
        next: () => this.router.navigate(['/productlist']),
        error: (err) => console.error('Error deleting product:', err)
      });
    }
  }
}
