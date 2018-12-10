import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = {};
  error = false;

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
  }

  login() {
    this.apiService.login(this.user).subscribe(response => {
      if (!response) {
        this.error = true;
      } else {
        this.apiService.authToken = response;
        this.router.navigate(['home']);
      }
    });
  }


}
