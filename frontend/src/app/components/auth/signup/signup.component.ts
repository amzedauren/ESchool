import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth.service';
import { User, AuthUser } from './../../../enteties/enteties';
import { Component, OnInit } from '@angular/core';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  public emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  public matcher = new MyErrorStateMatcher();

  user: AuthUser = new AuthUser();

  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit() {
  }

  signup() {
    this.authService.register(this.user).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/login'])
      },
      error => {
        console.log(error);
      }
    );

  }

}


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}