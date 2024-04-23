import { Component, Input } from '@angular/core';
import { BookService } from '../services/book.service';
import { Libro } from '../interfaces/libro';

@Component({
  selector: 'app-libro',
  templateUrl: './libro.component.html',
  styleUrls: ['./libro.component.css']
})
export class LibroComponent {
  @Input() id!: number;
  libro: Libro = {} as Libro;

  constructor(private bookService: BookService){}

  ngOnInit() {
    this.bookService.getBookById(this.id).subscribe(
      book => this.libro = book
    );
  }

}
