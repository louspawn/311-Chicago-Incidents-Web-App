import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

import { FormData } from '../_classes/form-data';
import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-report-incident',
  templateUrl: './report-incident.component.html',
  styleUrls: ['./report-incident.component.css']
})
export class ReportIncidentComponent implements OnInit {
  private _success = new Subject<string>();
  private _error = new Subject<string>();

  successMessage: string;
  errorMessage: string;

  // TODO: Statically gets hard-coded data for the form, must change to dynamic from database
  reportTypes = FormData.reportTypes;
  vehicleModel = FormData.vehicleModel;
  vehicleColor = FormData.vehicleColor;
  graffitiLocation = FormData.graffitiLocation;
  graffitiSurface = FormData.graffitiSurface;
  sanitationCodeNature = FormData.sanitationCodeNature;
  treeLocation = FormData.treeLocation;

  request = {
    id: null
  };

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this._success.subscribe((message) => this.successMessage = message);

    this._success.pipe(
      debounceTime(3000)
    ).subscribe(() => this.successMessage = null);

    this._error.subscribe((message) => this.errorMessage = message);
    this._error.pipe(
      debounceTime(3000)
    ).subscribe(() => this.errorMessage = null);
  }

  sendRequest() {
    this.apiService.addRequest(this.request).subscribe(response => {
      if (response) {
        this.changeSuccessMessage();
      } else {
        this.changeErrorMessage();
      }
    });
  }

  public changeSuccessMessage() {
    this._success.next(`Success!`);
  }

  public changeErrorMessage() {
    this._error.next(`Error!`);
  }

}
