import { Component, OnInit } from '@angular/core';

import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user = {};

  constructor(private apiService: ApiService) { }

  ngOnInit() {
  }

  register() {
    console.log(this.user);
    this.apiService.addUser(this.user).subscribe(response => {
      console.log(response);
    });
  }

}
