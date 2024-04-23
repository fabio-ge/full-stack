import { Component, OnInit } from '@angular/core';
import { BundleService } from '../services/bundle.service';
import { Bundle } from '../interfaces/bundle';
import { Observable, OperatorFunction, concatMap, forkJoin, from, toArray } from 'rxjs';
import { Libro } from '../interfaces/libro';
import { BookService } from '../services/book.service';
import { MessaggiService } from '../services/messaggi.service';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Component({
  selector: 'app-guarda',
  templateUrl: './guarda.component.html',
  styleUrls: ['./guarda.component.css']
})
export class GuardaComponent implements OnInit {
  elencoBundle$!: Observable<Bundle[]>;
  
  constructor(private bundleService: BundleService,
              private bookService: BookService,
              private messaggi: MessaggiService) {}

  ngOnInit() {
    this.elencoBundle$ = this.bundleService.getAll()
  }

  sgancia(bundleId: number | undefined) {
    if(bundleId){
      this.bundleService.delete(bundleId).subscribe(
        res => {
          this.elencoBundle$ = this.bundleService.getAll()
          this.messaggi.messaggio("Bundle cancellato",TIPI_MESSAGGIO.ok)
        }
      )
    }
  }

  venduto(bundleId: number | undefined) {
    if(bundleId){
      this.bundleService.getBundleById(bundleId).subscribe(
        (bundle) => {
          let elencoLibri = bundle.elencoLibri
          this.sgancia(bundleId)

          elencoLibri.forEach(
            id => {
              this.bookService.deleteBookById(id).subscribe(
                () => null
              )
            }
          )

        }
      )
    }
  }

}
