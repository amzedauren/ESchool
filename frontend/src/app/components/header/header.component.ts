import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  username:string = 'username'
  loggedIn:boolean = false;

  constructor(private authService:AuthService, private router: Router) {

  }

  ngOnInit() {
    this.authService.loggedInEvent.subscribe(weather => {
      this.loggedIn = weather;    
      if(weather) {
        this.username = this.authService.getUser().name
      }
      console.log(weather);  
    });

    this.authService.isLoggedIn();
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  
}
