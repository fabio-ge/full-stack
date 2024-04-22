import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessaggiService } from './messaggi.service';
import { Bundle } from '../interfaces/bundle';

@Injectable({
  providedIn: 'root'
})
export class BundleService {
  baseUrl = 'http://localhost:3000/bundles'

  constructor(private http: HttpClient) { }


  creaNuovoBundle(bundle: Bundle){
    return this.http.post(this.baseUrl,bundle)
  }            

}
