import { Routes } from '@angular/router';
import { PostComponent } from './entities/post/post.component';

export const routes: Routes = [
  { path: '', redirectTo: '/posts', pathMatch: 'full' },
  { path: 'posts', component: PostComponent },
];
