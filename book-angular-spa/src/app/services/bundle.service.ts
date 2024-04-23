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
  
  getAll() {
    return this.http.get<Bundle[]>(this.baseUrl)
  }

  getBundleById(bundleId: number) {
    return this.http.get<Bundle>(`${this.baseUrl}/${bundleId}`)
  }

  delete(bundleId: number) {
    return this.http.delete(`${this.baseUrl}/${bundleId}`)
  }

}
