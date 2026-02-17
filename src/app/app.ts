import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <nav class="navbar">
      <div class="nav-brand">Blog CMS</div>
      <div class="nav-links">
        <a routerLink="/posts">Posts</a>
      </div>
    </nav>
    <router-outlet></router-outlet>
  `,
  styles: [`
    .navbar { background: #1976d2; padding: 15px 20px; display: flex; justify-content: space-between; align-items: center; }
    .nav-brand { color: white; font-size: 20px; font-weight: bold; }
    .nav-links a { color: white; text-decoration: none; margin-left: 20px; }
  `]
})
export class App {
  title = 'blog-cms-angular';
}
