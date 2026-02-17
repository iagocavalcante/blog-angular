import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private resourceUrl = 'api/posts';

  public posts = signal<Post[]>([]);

  constructor(private http: HttpClient) {}

  create(post: Post): Observable<Post> {
    return this.http.post<Post>(this.resourceUrl, post);
  }

  update(post: Post): Observable<Post> {
    return this.http.put<Post>(`${this.resourceUrl}/${post.id}`, post);
  }

  find(id: number): Observable<Post> {
    return this.http.get<Post>(`${this.resourceUrl}/${id}`);
  }

  query(): Observable<Post[]> {
    return this.http.get<Post[]>(this.resourceUrl);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.resourceUrl}/${id}`);
  }

  loadAll(): void {
    this.query().subscribe({
      next: (posts) => this.posts.set(posts),
      error: (err) => console.error('Error loading posts', err)
    });
  }
}
