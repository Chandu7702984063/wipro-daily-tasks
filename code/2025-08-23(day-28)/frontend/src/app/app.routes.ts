import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { FoodsComponent } from './pages/foods/foods.component';

import { FruitsComponent } from './pages/fruits/fruits.component';
import { AuthGuard } from './services/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'foods', component: FoodsComponent, canActivate: [AuthGuard] },
  { path: 'fruits', component: FruitsComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }
];


