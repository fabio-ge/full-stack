import { Component, Input, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Libro } from '../interfaces/libro';
import { MessaggiService } from '../services/messaggi.service';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Component({
  selector: 'app-lista-libri',
  templateUrl: './lista-libri.component.html',
  styleUrls: ['./lista-libri.component.css']
})
export class ListaLibriComponent {
  
  @Input() libri: Libro[] = []

  constructor(private bookService: BookService, private messaggi: MessaggiService){}

  cancella(id: number | undefined) {
    if(id == null) return;
    this.bookService.deleteBookById(id).subscribe(
      res => {
        let index = this.libri.findIndex(el => el.id === id)
        this.libri.splice(index,1)
        this.messaggi.messaggio("Libro cancellato con successo",TIPI_MESSAGGIO.ok)
      }
    );

  }

}
