import { Component, OnInit } from '@angular/core';

import { FormData } from '../_classes/form-data';
import { ApiService } from '../_services/api.service';

@Component({
  selector: 'app-search-reports',
  templateUrl: './search-reports.component.html',
  styleUrls: ['./search-reports.component.css']
})
export class SearchReportsComponent implements OnInit {
  reportTypes = FormData.reportTypes;

  searchForm = {
    streetAddress: null,
    zipCode: null,
    page: 1,
    totalElements: 0
  };
  totalPerTypeForm = {
    page: 1,
    totalElements: 0,
    startDate: null,
    endDate: null
  };
  totalPerDayForm = {
    page: 1,
    totalElements: 0,
    startDate: null,
    endDate: null
  };
  mostCommonForm = {
    page: 1,
    totalElements: 0,
    date: null
  };

  searchResults;
  totalPerTypeResults;
  totalPerDayResults;
  mostCommonResults;

  dateNow;

  constructor(private apiService: ApiService) { }

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
    this.apiService.search(this.searchForm).subscribe( results => {
      if (results) {
        this.searchResults = (<any> results).content;
        this.searchForm.totalElements = (<any> results).totalElements;
      }
    });
  }

  findTotalPerType() {
    this.apiService.findTotalPerType(this.totalPerTypeForm).subscribe( results => {
      if (results) {
        this.totalPerTypeResults = (<any> results).content;
        this.totalPerTypeForm.totalElements = (<any> results).totalElements;
      }
    });
  }

  findTotalPerDay() {
    this.apiService.findTotalPerDay(this.totalPerDayForm).subscribe( results => {
      if (results) {
        this.totalPerDayResults = (<any> results).content;
        this.totalPerDayForm.totalElements = (<any> results).totalElements;
      }
    });
  }

  findMostCommonType() {
    this.apiService.findMostCommonType(this.mostCommonForm).subscribe( results => {
      if (results) {
        this.mostCommonResults = (<any> results).content;
        this.mostCommonForm.totalElements = (<any> results).totalElements;
      }
    });
  }

}
