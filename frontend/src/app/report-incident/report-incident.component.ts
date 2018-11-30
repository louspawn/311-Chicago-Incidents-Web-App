import { Component, OnInit } from '@angular/core';

import { FormData } from '../form-data';

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

  request = {};

  constructor() { }

  ngOnInit() {
  }

  sendRequest() {
    console.log(this.request);
  }

}
