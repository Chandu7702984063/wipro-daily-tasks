import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ITask } from '../models/task';

@Component({
  selector: 'app-task-list',
  standalone: true,
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {
  @Input() tasks: ITask[] = [];
  @Output() remove = new EventEmitter<number>();
}
