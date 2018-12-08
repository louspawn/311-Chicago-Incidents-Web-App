import { Component, OnInit } from '@angular/core';

import { FormData } from '../_classes/form-data';
import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-report-incident',
  templateUrl: './report-incident.component.html',
  styleUrls: ['./report-incident.component.css']
})
export class ReportIncidentComponent implements OnInit {
  // TODO: Statically gets hard-coded data for the form, must change to dynamic from database
  reportTypes = FormData.reportTypes;
  vehicleModel = FormData.vehicleModel;
  vehicleColor = FormData.vehicleColor;
  graffitiLocation = FormData.graffitiLocation;
  graffitiSurface = FormData.graffitiSurface;
  sanitationCodeNature = FormData.sanitationCodeNature;
  treeLocation = FormData.treeLocation;

  // request = {id: null};
  request = {
    id: null,
    creationDate: null,
    serviceRequestNumber: null,
    streetAddress: 'Address',
    latitude: 35,
    longitude: -90,
    streetNumber: 15,
    typeOfServiceRequest: 'ABANDONED_VEHICLE',
    xcoordinate: 121212.32123,
    ycoordinate: 12211212,
    zipCode: 123213
  };

  constructor(private apiService: ApiService) { }

  ngOnInit() {
  }

  sendRequest() {
    console.log(this.request);
    this.apiService.addRequest(this.request).subscribe(response => {
      console.log(response);
    });
  }

}
