import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Observable, Subscription } from 'rxjs';
import { Libro } from '../interfaces/libro';
import { Bundle } from '../interfaces/bundle';
import { BundleService } from '../services/bundle.service';
import { MessaggiService } from '../services/messaggi.service';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Component({
  selector: 'app-crea',
  templateUrl: './crea.component.html',
  styleUrls: ['./crea.component.css']
})
export class CreaComponent implements OnInit {
  libri$: Observable<Libro[]> = new Observable();
  newBundle!: Bundle;
  sub!: Subscription;

  constructor(private bookService: BookService,
              private bundleService: BundleService,
              private messaggi: MessaggiService
  ){
    this.newBundle = this.initEmptyBundle()
  }

  ngOnInit() {
    this.libri$ = this.bookService.getAll()
  }

  toggleValue(idLibro: number | undefined){
    if(!idLibro) return
    
    if(this.newBundle.elencoLibri.includes(idLibro)){
      let indexToRemove = this.newBundle.elencoLibri.findIndex(id => id === idLibro)
      this.newBundle.elencoLibri.splice(indexToRemove,1)
    }else{
      this.newBundle.elencoLibri.push(idLibro)
    }
  }

  saveBundle(titolo: string){
    this.newBundle.nome = titolo
    this.sub = this.bundleService.creaNuovoBundle(this.newBundle).subscribe(
      {
        next: () => {
          this.messaggi.messaggio("Bundle creato con successo",TIPI_MESSAGGIO.ok)
          this.newBundle = this.initEmptyBundle()
          this.resetInterface()
        },
        error: () => this.messaggi.messaggio("Non sono riuscito a creare un nuovo bundle",TIPI_MESSAGGIO.ko)
      }
    )
  }

  private initEmptyBundle(): Bundle{
    return {
      nome: '',
      elencoLibri: []
    }
  }

  private resetInterface() {
    let titolo: HTMLInputElement | null = document.querySelector('#titolo')
    let selezionati = document.querySelectorAll('.sel')

    if(titolo) titolo.value = ''
    selezionati.forEach((el: any) => el.checked = false)
  
  }

  ngOnDestroy() {
    if(this.sub) this.sub.unsubscribe()
  }

}
