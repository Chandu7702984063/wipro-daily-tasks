import { Component, OnInit } from '@angular/core';
import { FoodsService, FoodItem } from '../../services/foods.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-fruits',
  templateUrl: './fruits.component.html',
  styleUrls: ['./fruits.component.css'],
  imports: [FormsModule, CommonModule]
})
export class FruitsComponent implements OnInit {

  foods: FoodItem[] = [];   // all items from backend
  cart: FoodItem[] = [];    // user cart

  constructor(private foodService: FoodsService, private router: Router) { }

  ngOnInit(): void {
    // Load cart from localStorage
    this.cart = JSON.parse(localStorage.getItem('cart') || '[]');

    // Fetch all foods from backend
    this.foodService.getAll().subscribe({
      next: (data) => {
        console.log('Fetched foods:', data);
        this.foods = data;
      },
      error: (err) => console.error('Error fetching foods:', err)
    });
  }

  addToCart(item: FoodItem) {
    this.cart.push(item);
    localStorage.setItem('cart', JSON.stringify(this.cart));
    // alert(`${item.name} added to cart!`);
  }

  removeFromCart(index: number) {
    const removed = this.cart[index];
    this.cart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(this.cart));
    alert(`${removed.name} removed from cart!`);
  }

  getTotal(): number {
    return this.cart.reduce((acc, f) => acc + f.price, 0);
  }

  buyNow() {
    if (this.cart.length === 0) {
      alert('Cart is empty!');
      return;
    }
    const total = this.getTotal();
    alert(`✅ You bought ${this.cart.length} item(s). Total: ₹${total}`);
    this.cart = [];
    localStorage.removeItem('cart');
  }

  // ✅ Logout functionality
  logout() {
   
    localStorage.removeItem('cart');
    localStorage.removeItem('user');   
    localStorage.removeItem('token');  

    alert('You have been logged out!');
    this.router.navigate(['/login']);  
  }
}
