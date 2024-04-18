import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

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
              private snack: MatSnackBar){}

  login() {
    let { username, password } = this.loginForm.value
    this.authService.getUser(username!).subscribe(
      (users) => {
        if(users.length && users[0].password == password){
          sessionStorage.setItem('SESSID','assasa')
          this.router.navigate(['/home'])
        }else {
          this.snack.open("Autenticazione fallita",'',{
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          })
        }
      }
    );
  }

}
