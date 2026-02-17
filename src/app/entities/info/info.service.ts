import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Info } from './info.model';

@Injectable({
  providedIn: 'root'
})
export class InfoService {
  private resourceUrl = 'api/infos';

  public info = signal<Info | null>(null);

  constructor(private http: HttpClient) {}

  create(info: Info): Observable<Info> {
    return this.http.post<Info>(this.resourceUrl, info);
  }

  update(info: Info): Observable<Info> {
    return this.http.put<Info>(`${this.resourceUrl}/${info.id}`, info);
  }

  find(id: number): Observable<Info> {
    return this.http.get<Info>(`${this.resourceUrl}/${id}`);
  }

  query(): Observable<Info[]> {
    return this.http.get<Info[]>(this.resourceUrl);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.resourceUrl}/${id}`);
  }
}
