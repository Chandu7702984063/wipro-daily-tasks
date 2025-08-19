import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ITask } from '../models/task';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css'] 
})
export class TaskComponent {
  description = '';
  category = '';


  @Output() add = new EventEmitter<ITask>();

  onSubmit() {
    const desc = this.description.trim();
    if (!desc) return;
    this.add.emit({ description: desc, category: this.category });
    this.description = '';
    this.category = '';
  }
}
