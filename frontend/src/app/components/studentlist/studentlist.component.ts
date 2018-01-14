import { RemoveFromGroupComponent } from './remove-from-group/remove-from-group.component';
import { Student } from './../../enteties/enteties';
import { EschoolService } from './../../services/eschool.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { AddToGroupComponent } from './add-to-group/add-to-group.component';

@Component({
  selector: 'app-studentlist',
  templateUrl: './studentlist.component.html',
  styleUrls: ['./studentlist.component.scss']
})
export class StudentlistComponent implements OnInit {

  constructor(private eschoolService: EschoolService, public dialog: MatDialog) { }
  displayedColumns = ['name', 'phone number', 'action'];

  public students: Student[];
  public dataSource
  status: string

  newStudent: Student = {} as Student;

  addToGroupDialog(student: Student): void {
    let dialogRef = this.dialog.open(AddToGroupComponent, {
      width: '250px',
      data: { student: student },

    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      // this.animal = result;
    });
  }


  removeFromGroupDialog(student: Student): void {
    console.log('student')

    let dialogRef = this.dialog.open(RemoveFromGroupComponent, {
      width: '250px',
      data: { student: student },

    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  public update() {
    this.eschoolService.getAllStudents().subscribe(data => {
      console.log(data)
      this.students = data as Student[];
      this.dataSource = new MatTableDataSource<Student>(this.students);
    }, error => {
      console.log(error);
    })
  }

  public add() {
    this.eschoolService.addStudent(this.newStudent).subscribe(data => {
      if (data['status'] == 'ok') {
        this.students.push(data['data'])
        this.dataSource = new MatTableDataSource<Student>(this.students);
      } else {
        this.status = 'something went wrong!'
      }
    }, error => {
      console.log(error)
    })

    this.newStudent.name = ''
    this.newStudent.email = ''
    this.newStudent.phoneNumber = ''

  }

  ngOnInit() {
    this.update()
  }

}
