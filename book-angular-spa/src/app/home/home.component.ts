import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BookService } from '../services/book.service';
import { Libro } from '../interfaces/libro';
import { MessaggiService } from '../services/messaggi.service';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  formAggiungi = this.fb.group({
    autore: ['', [Validators.required]],
    titolo: ['', Validators.required],
    edizione: [''],
    condizioni: ['']
  })

  libri: Libro[] = [];

  constructor(private fb: FormBuilder, 
              private bookService: BookService,
              private messaggiService: MessaggiService){}

  ngOnInit() {
    this.bookService.getAll().subscribe(
      res => this.libri = res
    )
  }

  aggiungi() {
    this.bookService.postLibro(this.formAggiungi.value as Libro).subscribe({
                      'next': (res) => {
                        this.libri.push(res as Libro)
                        this.messaggiService.messaggio("libro aggiunto",TIPI_MESSAGGIO.ok)
                      },
                      'error': error => console.log(error)
                    }  
    );
  }

  cerca(term: string) {
    this.bookService.findByTitolo(term).subscribe(
      res => this.libri = res
    )
  }

}
