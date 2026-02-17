import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comentario } from './comentario.model';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {
  private resourceUrl = 'api/comentarios';

  public comentarios = signal<Comentario[]>([]);

  constructor(private http: HttpClient) {}

  create(comentario: Comentario): Observable<Comentario> {
    return this.http.post<Comentario>(this.resourceUrl, comentario);
  }

  update(comentario: Comentario): Observable<Comentario> {
    return this.http.put<Comentario>(`${this.resourceUrl}/${comentario.id}`, comentario);
  }

  find(id: number): Observable<Comentario> {
    return this.http.get<Comentario>(`${this.resourceUrl}/${id}`);
  }

  query(): Observable<Comentario[]> {
    return this.http.get<Comentario[]>(this.resourceUrl);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.resourceUrl}/${id}`);
  }
}
