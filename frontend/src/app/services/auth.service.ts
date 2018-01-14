import { config } from './../config';
import { Cookie } from 'ng2-cookies/ng2-cookies';
import { User, AuthUser } from './../enteties/enteties';
import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { HttpResponse } from '@angular/common/http/src/response';
import { HttpParams } from '@angular/common/http/src/params';
import { error } from 'util';


@Injectable()
export class AuthService {

  private loggedIn: boolean = false;
  public loggedInEvent = new EventEmitter<boolean>();
  private user: User;

  private url = config.url;
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }), withCredentials: true };

  constructor(private http: HttpClient) { }

  public getLoggedIn(){
    return this.loggedIn;
  }

  public isLoggedIn() {
    let url = this.url + '/user/Me'

    this.http.get(url,  this.httpOptions).subscribe(data => {
      console.log(data);
      this.user = data as User
      this.loggedInEvent.next(true)
      this.loggedIn = true;
    }, error => {
      this.loggedIn = false;
      // this.loggedInEvent.next(false)
      // console.log(error);
    })
  }

  public getUser(){
    return this.user;
  }

  public login(email: string, password: string) {
    let url = this.url + '/user/login';

    let data = new URLSearchParams();
    data.append('email', email);
    data.append('password', password);

    this.http.post(url, data.toString(), this.httpOptions).subscribe(
      data => {
        if (data['status'] === "fail") {
          this.loggedInEvent.next(false);
          this.loggedIn = false;
        } else {
          this.user = data['data'] as User
          this.loggedIn = true;
          this.loggedInEvent.next(true);
          
        }
      },
      error => {
        console.log('error')
        console.log(error);
        this.loggedIn = false;
        this.loggedInEvent.next(false);
      }
    );


  }

  public register(user: AuthUser) {
    let url = this.url + '/user/register';
    let data = new URLSearchParams();
    data.append('email', user.email);
    data.append('password', user.password);
    data.append('name', user.name);

    return this.http.post(url, data.toString(), this.httpOptions)

  }

  public logout() {
    this.login("lol", "lol") 
    this.loggedIn = false;
    this.loggedInEvent.next(false);
  }


}
