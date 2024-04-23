import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Libro } from '../interfaces/libro';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  baseurl = 'http://localhost:3000/books'
  
  constructor(private http: HttpClient) { }

  postLibro(payloadLibro: Libro) {
    return this.http.post(this.baseurl, payloadLibro);
  }

  getAll(): Observable<Libro[]> {
    return this.http.get<Libro[]>(this.baseurl);
  }

  findByTitolo(titolo: string): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.baseurl}?titolo_like=${titolo}`)
  }

  getBookById(id: number): Observable<Libro> {
    return this.http.get<Libro>(`${this.baseurl}/${id}`)
  }

  deleteBookById(id: number) {
    return this.http.delete(`${this.baseurl}/${id}`)
  }

}
