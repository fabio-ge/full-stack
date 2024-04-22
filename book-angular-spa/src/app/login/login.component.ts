import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MessaggiService } from '../services/messaggi.service';
import { TIPI_MESSAGGIO } from '../interfaces/tipi-messaggio';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required] 
  })

  constructor(private fb: FormBuilder, 
              private authService: AuthService, 
              private router: Router,
              private messaggi: MessaggiService){}

  login() {
    let { username, password } = this.loginForm.value
    this.authService.getUser(username!).subscribe(
      (users) => {
        if(users.length && users[0].password == password){
          sessionStorage.setItem('SESSID','assasa')
          this.router.navigate(['/home'])
        }else {
            this.messaggi.messaggio("autenticazione fallita",TIPI_MESSAGGIO.ko)
        }
      },
      (error) => this.messaggi.messaggio("problemi di comunicazione con il server", TIPI_MESSAGGIO.ko)
    );
  }

}
