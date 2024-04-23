import { Injectable, NgZone } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Injectable({
  providedIn: 'root'
})
export class MessaggiService {

  constructor(private snack: MatSnackBar) { }

  messaggio(testo: string, tipo: TIPI_MESSAGGIO) {
      this.snack.open(testo,'',{
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000,
        panelClass: tipo === TIPI_MESSAGGIO.ok ? 'success' : 'error'
      })
  }
}
