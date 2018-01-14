import { Group } from './../../../enteties/enteties';
import { EschoolService } from './../../../services/eschool.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-remove-from-group',
  templateUrl: './remove-from-group.component.html',
  styleUrls: ['./remove-from-group.component.scss']
})
export class RemoveFromGroupComponent implements OnInit {

  selected:string
  groups: Group[]
  status:string
  constructor(
    public dialogRef: MatDialogRef<RemoveFromGroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private eschoolService: EschoolService) { }

  ngOnInit() {
    // this.groups = this.data['student'].groups
    this.eschoolService.getAllGroups().subscribe(data => {
      this.groups = data as Group[]
    }, error => {
    })
  }

  remove(){
    console.log(this.data['student'].groups)

    this.eschoolService.removeStudentFromGroup(this.selected, this.data['student'].id).subscribe(data=>{
      this.eschoolService.groupsChanged.next(true)
      this.status = 'ok'
      this.dialogRef.close()
    }, error=>{
    })

  }

}
