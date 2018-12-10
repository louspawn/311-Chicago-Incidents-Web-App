import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:4200/';  // URL to web api

  public authToken = {token: null, email: null};

  constructor(private http: HttpClient) {}

  /** GET: check if email already exists */
  emailExists (email): Observable<boolean> {
    const url = this.apiUrl + 'users/email_exists/' + email;
    return this.http.get<boolean>(url).pipe(
        tap(_ => console.log('email_checked')),
        catchError(this.handleError('emailExists', true))
      );
  }

  /** GET: search with zipcode and address */
  search (searchTerms): Observable<any[]> {
    const url = this.apiUrl + 'search/zipCode=' + (searchTerms.zipCode ? searchTerms.zipCode : '') +
                              '&streetAddress=' + (searchTerms.streetAddress ? searchTerms.streetAddress : '') +
                              '?page=' + (searchTerms.page - 1);
    return this.http.get<any[]>(url).pipe(
        tap(_ => console.log('searched ' + url)),
        catchError(this.handleError('search', []))
      );
  }

  /** GET: search total per type */
  findTotalPerType (searchTerms): Observable<any[]> {
    const url = this.apiUrl + 'search1/fromDate=' + (searchTerms.startDate ?
                               searchTerms.startDate.year + '-' + searchTerms.startDate.month + '-' + searchTerms.startDate.day : '') +
                              '&toDate=' + (searchTerms.endDate ?
                                searchTerms.endDate.year + '-' + searchTerms.endDate.month + '-' + searchTerms.endDate.day : '') +
                              '?page=' + (searchTerms.page - 1);
    return this.http.get<any[]>(url).pipe(
        tap(_ => console.log('searched ' + url)),
        catchError(this.handleError('search1', []))
      );
  }

  /** GET: search total per type */
  findTotalPerDay (searchTerms): Observable<any[]> {
    const url = this.apiUrl + 'search2/fromDate=' + (searchTerms.startDate ?
                               searchTerms.startDate.year + '-' + searchTerms.startDate.month + '-' + searchTerms.startDate.day : '') +
                              '&toDate=' + (searchTerms.endDate ?
                                searchTerms.endDate.year + '-' + searchTerms.endDate.month + '-' + searchTerms.endDate.day : '') +
                              '&type=' +  (searchTerms.type ? searchTerms.type : '') +
                              '?page=' + (searchTerms.page - 1);
    return this.http.get<any[]>(url).pipe(
        tap(_ => console.log('searched ' + url)),
        catchError(this.handleError('search2', []))
      );
  }

  /** GET: search most common types per zipcode */
  findMostCommonType (searchTerms): Observable<any[]> {
    const url = this.apiUrl + 'search3/date=' + (searchTerms.date ?
                                searchTerms.date.year + '-' + searchTerms.date.month + '-' + searchTerms.date.day : '') +
                              '?page=' + (searchTerms.page - 1);
    return this.http.get<any[]>(url).pipe(
        tap(_ => console.log('searched ' + url)),
        catchError(this.handleError('search3', []))
      );
  }

  /** POST: attemp to login */
  login(user): Observable<any> {
    const url = this.apiUrl + 'users/login';
    const credentials = {email: user.email, password: user.password};
    console.log(url);
    return this.http.post<any>(url, credentials, httpOptions).pipe(
      tap((response) => console.log(`login for user w/ email=${response.email}`)),
      catchError(this.handleError<any>('login'))
    );
  }

  //////// Save methods //////////

  /** POST: add a new user to the server */
  addUser (user): Observable<any> {
    const url = this.apiUrl + 'users';
    console.log(url);
    return this.http.post<any>(url, user, httpOptions).pipe(
      tap((response) => console.log(`added user w/ id=${response.id}`)),
      catchError(this.handleError<any>('addUser'))
    );
  }

  /** POST: add a new request to the server */
  addRequest (request): Observable<any> {
    const url = this.apiUrl + this.getUrlPathForType(request.typeOfServiceRequest);
    console.log(url);
    return this.http.post<any>(url, request, httpOptions).pipe(
      tap((response) => console.log(`added request w/ id=${response.id}`)),
      catchError(this.handleError<any>('addRequest'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  resetToken() {
    this.authToken = {token: null, email: null};
  }

  getUrlPathForType(typeOfRequest: String): String {
    switch (typeOfRequest) {
      case 'ABANDONED_VEHICLE':
        return 'abandoned_vehicles_requests';
      case 'GARBAGE_CARTS':
        return 'garbage_carts_requests';
      case 'GRAFFITI_REMOVAL':
        return 'graffiti_removal_requests';
      case 'POT_HOLES':
        return 'pot_holes_requests';
      case 'RODENT_BAITING':
        return 'rodent_baiting_requests';
      case 'SANITATION_CODE_COMPLAINTS':
        return 'sanitation_code_complaints_requests';
      case 'TREE_DEBRIS':
        return 'tree_debris_requests';
      case 'TREE_TRIMS':
        return 'tree_trims_requests';
      default:
        return 'requests';
    }
  }

}
