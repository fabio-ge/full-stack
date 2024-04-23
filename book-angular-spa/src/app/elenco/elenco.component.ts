import { Component } from '@angular/core';
import {  ActivatedRoute } from '@angular/router';
import { Libro } from '../interfaces/libro';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-elenco',
  templateUrl: './elenco.component.html',
  styleUrls: ['./elenco.component.css']
})
export class ElencoComponent {
  id: number = 0;
  libri$!: Observable<number[]>
  constructor(private router: ActivatedRoute){}
  
  ngOnInit() {
    this.id = parseInt(this.router.snapshot.paramMap.get('bundleId') || '0')
    if(this.id){
      null
    }else{
      null
    }
  }
}
