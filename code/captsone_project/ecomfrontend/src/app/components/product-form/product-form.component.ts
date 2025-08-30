import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,       // ✅ mark standalone
  imports: [FormsModule],
  selector: 'app-product-form',
  templateUrl: './product-form.component.html'
})
export class ProductFormComponent implements OnInit {
  product: Product = { name: '', description: '', price: 0, quantity: 0 };
  isEdit = false;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.productService.get(+id).subscribe(p => this.product = p);
    }
  }

  save() {
    if (this.isEdit) {
      this.productService.update(this.product).subscribe(() => this.router.navigate(['/admin/products']));
    } else {
      this.productService.create(this.product).subscribe(() => this.router.navigate(['/admin/products']));
    }
  }
}
