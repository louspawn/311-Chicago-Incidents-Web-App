import { Component } from '@angular/core';

import { ApiService } from './_services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '311 Chicago Incidents';

  constructor(private apiService: ApiService) { }

}
