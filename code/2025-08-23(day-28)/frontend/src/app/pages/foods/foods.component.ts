import { Component, OnInit } from '@angular/core';
import { FoodsService, FoodItem } from '../../services/foods.service';
import { FormsModule } from '@angular/forms';   // Import FormsModule
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  standalone: true,
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.css'],
  imports: [FormsModule, CommonModule]
})
export class FoodsComponent implements OnInit {

  foods: FoodItem[] = [];
  foodForm: FoodItem = { name: '', description: '', price: 0, imageUrl: '' };
  editId: number | null = null;

  constructor(private foodService: FoodsService) { }

  ngOnInit(): void {
    this.loadFoods();
  }

  loadFoods() {
    this.foodService.getAll().subscribe(data => this.foods = data);
  }

  submit() {
    if (this.editId) {
      this.foodService.update(this.editId, this.foodForm).subscribe(() => {
        this.loadFoods();
        this.resetForm();
      });
    } else {
      this.foodService.add(this.foodForm).subscribe(() => {
        this.loadFoods();
        this.resetForm();
      });
    }
  }

  edit(food: FoodItem) {
    this.editId = food.id || null;
    this.foodForm = { ...food };
  }

  delete(id?: number) {
    if (!id) return;
    this.foodService.delete(id).subscribe(() => this.loadFoods());
  }

  resetForm() {
  this.editId = null;
  this.foodForm = { name: '', description: '', price: 0, imageUrl: '' };
}

}
