import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';


@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html'
})
export class PurchaseComponent implements OnInit {
  products: Product[] = [];
  selectedProduct?: Product;
  purchaseQty: number = 1;
  errorMessage: string = '';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => this.products = data);
  }

  placeOrder() {
    if (!this.selectedProduct) {
      this.errorMessage = 'Please select a product';
      return;
    }
    if (this.purchaseQty <= 0) {
      this.errorMessage = 'Quantity must be greater than 0';
      return;
    }
    if (this.purchaseQty > this.selectedProduct.qty) {
      this.errorMessage = 'Not enough stock available!';
      return;
    }

    this.productService.placeOrder(this.selectedProduct.id!, this.purchaseQty)
      .subscribe({
        next: () => {
          alert('Order placed successfully');
          this.errorMessage = '';
          this.productService.getProducts().subscribe(data => this.products = data);
        },
        error: err => {
          this.errorMessage = err.error?.message || 'Error placing order';
        }
      });
  }
}

