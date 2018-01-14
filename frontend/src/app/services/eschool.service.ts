import { config } from './../config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { Student } from '../enteties/enteties';

@Injectable()
export class EschoolService {


  private url = config.url
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }), withCredentials: true };
  public groupsChanged = new EventEmitter<any>();
  

  constructor(private http: HttpClient) { }

  public getAllUsers(){
    let url = this.url + '/user/getAll';
    return this.http.get(url, { withCredentials: true })
  }

  public addGroup(groupName:string, teacherId:string){
    let url = this.url + '/group/add';
    let data = new URLSearchParams();
    data.append('groupName', groupName);
    data.append('teacherId', teacherId);
  
    return this.http.post(url, data.toString(), this.httpOptions) 
  }

  public getAllStudents() {
    let url = this.url + '/student/getAll';
    return this.http.get(url, { withCredentials: true })
  }

  public getAllGroups() {
    let url = this.url + '/group/getAll';
    return this.http.get(url, { withCredentials: true })
  }

  public addStudent(student:Student){
    let url = this.url + '/student/add';
    let data = new URLSearchParams();
    data.append('email', student.email);
    data.append('phoneNumber', student.phoneNumber);
    data.append('name', student.name);
    
    return this.http.post(url, data.toString(), this.httpOptions) 
  }

  public addStudentToGroup(g_id:string, s_id:string){

    let url = this.url + '/group/addStudent';
    let data = new URLSearchParams();
    data.append('groupId', g_id);
    data.append('studentId', s_id);
    
    return this.http.post(url, data.toString(), this.httpOptions) 

  }

  removeStudentFromGroup(g_id:string, s_id:string){

    console.log(g_id);
    console.log(s_id);
    
    let url = this.url + '/group/removeStudent';
    let data = new URLSearchParams();
    data.append('groupId', g_id);
    data.append('studentId', s_id);
    
    return this.http.post(url, data.toString(), this.httpOptions) 

  }

}
