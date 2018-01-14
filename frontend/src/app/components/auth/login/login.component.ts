import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }
 
  pressed: boolean = false;
  username:string = '';
  password:string = ''; 
  status:String = '';
  disabledButton:boolean = false;

  ngOnInit() {
    this.authService.loggedInEvent.subscribe(data => {
      if(data === false) {
        if(this.pressed)
          this.status = 'login or password is incorrect!';
        this.disabledButton = false;
      } else{
        this.router.navigate(['']);
      }
    });
  }

  login(){
    this.pressed = true;
    // TODO
    this.disabledButton = true;
    this.authService.login(this.username, this.password);
  }
}
