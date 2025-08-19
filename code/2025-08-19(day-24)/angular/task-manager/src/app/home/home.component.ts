import { Component } from '@angular/core';
import { TaskComponent } from '../task/task.component';
import { TaskListComponent } from '../task-list/task-list.component';
import { ITask } from '../models/task';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TaskComponent, TaskListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
  private seq = 1;
  private nextId(): number { return this.seq++; }
  tasks: ITask[] = [
  ];

  addTask(task: ITask) {
  const newTask: ITask = {
    id: this.nextId(),
    description: task.description,
    category: task.category
  };

  this.tasks = [newTask, ...this.tasks];
}
  deleteTask(id: number) {
    this.tasks = this.tasks.filter(t => t.id !== id);
  }
}
