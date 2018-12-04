import { Component, OnInit } from '@angular/core';

import { FormData } from '../_classes/form-data';

@Component({
  selector: 'app-search-reports',
  templateUrl: './search-reports.component.html',
  styleUrls: ['./search-reports.component.css']
})
export class SearchReportsComponent implements OnInit {
  reportTypes = FormData.reportTypes;

  searchForm = {};
  totalPerTypeForm = {};
  totalPerDayForm = {};
  mostCommonForm = {};

  dateNow;

  constructor() { }

  ngOnInit() {
    const dateObj = new Date();
    const month = dateObj.getUTCMonth() + 1;
    const day = dateObj.getUTCDate();
    const year = dateObj.getUTCFullYear();

    this.dateNow = {
      year: year,
      month: month,
      day: day
    };
  }

  search() {
    console.log(this.searchForm);
  }

  findTotalPerType() {
    console.log(this.totalPerTypeForm);
  }

  findTotalPerDay() {
    console.log(this.totalPerDayForm);
  }

  findMostCommonType() {
    console.log(this.mostCommonForm);
  }

}
