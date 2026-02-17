import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { PostService } from './post.service';
import { Post } from './post.model';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [CommonModule, RouterLink],
  template: `
    <div class="container">
      <h1>Posts</h1>
      <a routerLink="/posts/new" class="btn btn-primary">Novo Post</a>
      
      <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Conteúdo</th>
            <th>Tags</th>
            <th>Status</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          @for (post of postService.posts(); track post.id) {
            <tr>
              <td>{{ post.id }}</td>
              <td>{{ post.conteudo }}</td>
              <td>{{ post.tags }}</td>
              <td>{{ post.status ? 'Ativo' : 'Inativo' }}</td>
              <td>
                <a [routerLink]="['/posts', post.id]">Ver</a>
              </td>
            </tr>
          }
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    .container { padding: 20px; }
    .table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    .table th, .table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    .table th { background: #f5f5f5; }
    .btn { padding: 8px 16px; background: #1976d2; color: white; text-decoration: none; border-radius: 4px; }
  `]
})
export class PostComponent implements OnInit {
  postService = inject(PostService);

  ngOnInit() {
    this.postService.loadAll();
  }
}
