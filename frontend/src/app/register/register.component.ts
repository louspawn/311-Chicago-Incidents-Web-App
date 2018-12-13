import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user = {};
  emailExists = false;

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
  }

  register() {
    console.log(this.user);
    this.apiService.addUser(this.user).subscribe(response => {
      if (response) {
        this.apiService.login(this.user).subscribe(authToken => {
          if (!authToken) {
            this.router.navigate(['login']);
          } else {
            this.apiService.authToken = authToken;
            this.router.navigate(['home']);
          }
        });
      } else {
        this.emailExists = true;
      }
    });
  }

  emailCheck(isInvalid) {
    if (!isInvalid) {
      this.apiService.emailExists((<any>this.user).email).subscribe(exists => {
        if (exists) {
          this.emailExists = true;
        } else {
          this.emailExists = false;
        }
      });
    }
  }

}
