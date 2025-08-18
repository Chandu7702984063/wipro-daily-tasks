import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],   // 👈 This is required for *ngFor, *ngIf, etc.
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
 books = [
  { title: 'Book 1', description: 'An inspiring story about courage and hope.', image: 'https://picsum.photos/200/300?random=1' },
  { title: 'Book 2', description: 'A thrilling mystery filled with twists and suspense.', image: 'https://picsum.photos/200/300?random=2' },
  { title: 'Book 3', description: 'Learn the secrets of success from great leaders.', image: 'https://picsum.photos/200/300?random=3' },
  { title: 'Book 4', description: 'A journey through history with fascinating insights.', image: 'https://picsum.photos/200/300?random=4' },
  { title: 'Book 5', description: 'A heartwarming tale about friendship and love.', image: 'https://picsum.photos/200/300?random=5' },
  { title: 'Book 6', description: 'Discover the wonders of science in simple words.', image: 'https://picsum.photos/200/300?random=6' },
  { title: 'Book 7', description: 'A gripping adventure set in distant lands.', image: 'https://picsum.photos/200/300?random=7' },
  { title: 'Book 8', description: 'Practical tips for personal growth and happiness.', image: 'https://picsum.photos/200/300?random=8' },
  { title: 'Book 9', description: 'A magical fantasy filled with mythical creatures.', image: 'https://picsum.photos/200/300?random=9' },
  { title: 'Book 10', description: 'Explore the power of positive thinking.', image: 'https://picsum.photos/200/300?random=10' },
  { title: 'Book 11', description: 'A detective story with unexpected turns.', image: 'https://picsum.photos/200/300?random=11' },
  { title: 'Book 12', description: 'Discover ancient civilizations and their secrets.', image: 'https://picsum.photos/200/300?random=12' },
  { title: 'Book 13', description: 'An emotional drama about family bonds.', image: 'https://picsum.photos/200/300?random=13' },
  { title: 'Book 14', description: 'Master new skills with easy-to-follow lessons.', image: 'https://picsum.photos/200/300?random=14' },
  { title: 'Book 15', description: 'An exciting sci-fi adventure into the future.', image: 'https://picsum.photos/200/300?random=15' },
  { title: 'Book 16', description: 'Simple steps to build healthy daily habits.', image: 'https://picsum.photos/200/300?random=16' },
  { title: 'Book 17', description: 'A classic novel that inspires generations.', image: 'https://picsum.photos/200/300?random=17' },
  { title: 'Book 18', description: 'A motivational guide to achieving your goals.', image: 'https://picsum.photos/200/300?random=18' },
  { title: 'Book 19', description: 'A lighthearted comedy with witty characters.', image: 'https://picsum.photos/200/300?random=19' },
  { title: 'Book 20', description: 'Timeless wisdom to live a better life.', image: 'https://picsum.photos/200/300?random=20' }
];

}
