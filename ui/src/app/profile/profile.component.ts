import {Component, Input, OnInit} from '@angular/core';
import {Profile} from '../profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  @Input()
  profile: Profile;

  constructor() {
  }

  ngOnInit() {
  }

}
