import { EschoolService } from './../../../services/eschool.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Group } from '../../../enteties/enteties';

@Component({
  selector: 'app-add-to-group',
  templateUrl: './add-to-group.component.html',
  styleUrls: ['./add-to-group.component.scss']
})
export class AddToGroupComponent implements OnInit {

  selected: string = ''
  status: string = ''

  groups: Group[];
  constructor(
    public dialogRef: MatDialogRef<AddToGroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private eschoolService: EschoolService) { }


  ngOnInit() {    
    this.eschoolService.getAllGroups().subscribe(data => {
      this.groups = data as Group[]
    }, error => {
      
    })
  }

  add() {
    console.log(this.data)
    this.eschoolService.addStudentToGroup(this.selected, this.data['student'].id).subscribe(data => {
      if (data['status'] == 'fail') {
        this.status = 'already added'
      } else {
        this.status = 'ok';
        this.eschoolService.groupsChanged.next(true);
        this.dialogRef.close()
      }
    }, error => {

    })
  }

}
