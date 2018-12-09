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

  /** GET heroes from the server */

  // /** GET heroes from the server */
  // getHeroes (): Observable<Hero[]> {
  //   return this.http.get<Hero[]>(this.heroesUrl)
  //     .pipe(
  //       tap(_ => this.log('fetched heroes')),
  //       catchError(this.handleError('getHeroes', []))
  //     );
  // }

  // /** GET hero by id. Return `undefined` when id not found */
  // getHeroNo404<Data>(id: number): Observable<Hero> {
  //   const url = `${this.heroesUrl}/?id=${id}`;
  //   return this.http.get<Hero[]>(url)
  //     .pipe(
  //       map(heroes => heroes[0]), // returns a {0|1} element array
  //       tap(h => {
  //         const outcome = h ? `fetched` : `did not find`;
  //         this.log(`${outcome} hero id=${id}`);
  //       }),
  //       catchError(this.handleError<Hero>(`getHero id=${id}`))
  //     );
  // }

  // /** GET hero by id. Will 404 if id not found */
  // getHero(id: number): Observable<Hero> {
  //   const url = `${this.heroesUrl}/${id}`;
  //   return this.http.get<Hero>(url).pipe(
  //     tap(_ => this.log(`fetched hero id=${id}`)),
  //     catchError(this.handleError<Hero>(`getHero id=${id}`))
  //   );
  // }

  // /* GET heroes whose name contains search term */
  // searchHeroes(term: string): Observable<Hero[]> {
  //   if (!term.trim()) {
  //     // if not search term, return empty hero array.
  //     return of([]);
  //   }
  //   return this.http.get<Hero[]>(`${this.heroesUrl}/?name=${term}`).pipe(
  //     tap(_ => this.log(`found heroes matching "${term}"`)),
  //     catchError(this.handleError<Hero[]>('searchHeroes', []))
  //   );
  // }

  login(user): Observable<any> {
    const url = this.apiUrl + 'users/login';
    const credentials = {email: user.email, password: user.password};
    console.log(url);
    return this.http.post<any>(url, credentials, httpOptions).pipe(
      tap((response) => console.log(`attempted login for user w/ email=${response.email}`)),
      catchError(this.handleError<any>('attemptedLogin'))
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
