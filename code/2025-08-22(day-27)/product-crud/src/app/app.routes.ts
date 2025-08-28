import { Routes } from '@angular/router';
import { ProductAdd } from './productadd/productadd.component';
import { ProductEditComponent } from './productedit/productedit.component';
import { ProductDelete } from './productdelete/productdelete.component';
import { ProductList} from './productlist/productlist.component';

export const routes: Routes = [
  { path: '', redirectTo: 'productlist', pathMatch: 'full' },
  { path: 'productlist', component: ProductList },
  { path: 'productadd', component: ProductAdd },
  { path: 'productedit/:id', component: ProductEditComponent },
  { path: 'productdelete/:id', component: ProductDelete }
];
