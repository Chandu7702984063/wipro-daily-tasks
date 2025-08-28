import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Order } from '../models/order';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html'
})
export class OrderHistoryComponent implements OnInit {
  orders: Order[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getOrderHistory().subscribe(data => this.orders = data);
  }
}

