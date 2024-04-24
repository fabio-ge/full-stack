import { Component } from '@angular/core';
import {  ActivatedRoute } from '@angular/router';
import { Libro } from '../interfaces/libro';
import { Observable } from 'rxjs';
import { BookService } from '../services/book.service';
import { BundleService } from '../services/bundle.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-elenco',
  templateUrl: './elenco.component.html',
  styleUrls: ['./elenco.component.css']
})
export class ElencoComponent {
  id: number = 0;
  libriBundle: Libro[] = []
  libri$!: Observable<Libro[]>
  autoreChecked = true
  titoloChecked = true
  editoreChecked = true
  condizioniChecked = true

  constructor(private router: ActivatedRoute,
              private bookService: BookService,
              private bundleService: BundleService
  ){}
  
  ngOnInit() {
    this.id = parseInt(this.router.snapshot.paramMap.get('bundleId') || '0')
    if(this.id){
      this.bundleService.getBundleById(this.id)
                        .subscribe(bundle =>
                            bundle.elencoLibri.forEach(id => {
                              this.bookService.getBookById(id)
                                              .subscribe(book => this.libriBundle.push(book))
                            })
                        )
    }else{
      this.libri$ = this.bookService.getAll()
    }
  }

}
