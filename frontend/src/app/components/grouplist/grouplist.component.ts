import { User } from './../../enteties/enteties';
import { EschoolService } from './../../services/eschool.service';
import { Component, OnInit } from '@angular/core';
import { Group } from '../../enteties/enteties';
import { error } from 'util';

@Component({
  selector: 'app-grouplist',
  templateUrl: './grouplist.component.html',
  styleUrls: ['./grouplist.component.scss']
})
export class GrouplistComponent implements OnInit {

  constructor(private eschoolService: EschoolService) { }
  status:string;
  groupName:string;
  selected: string;
  public groups: Group[]
  public users: User[]

  ngOnInit() {
    this.update()
    this.eschoolService.groupsChanged.subscribe(data => {
      this.update()
    })

  }

  public update() {
    this.eschoolService.getAllGroups().subscribe(data => {
      this.groups = data as Group[]
    }, error => {
      console.log(error)
    })

    this.eschoolService.getAllUsers().subscribe(data => {
      this.users = data as User[]
    }, error => {
      console.log(error)
    })

  }

  public add() {
    this.eschoolService.addGroup(this.groupName, this.selected).subscribe(data => {
      if(data['status'] == "ok") {
        this.groups.push(data['data'])
      } else {
        this.status = 'something went wrong!';
      }
    }, error => {
      console.log(error)
    })

    this.groupName = ''

  }

}
