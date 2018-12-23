import {Injectable} from "@angular/core";
import {Item} from "./item";
import {HttpClient,HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError,tap} from "rxjs/operators";



@Injectable({
  providedIn:"root"
})
export class ItemService{

  private itemsUrl='http://localhost:8080/item';
  items : Item[];

  constructor(private http:HttpClient){}

  getItems(): Observable<Item[]>{
    return this.http.get<Item[]>(this.itemsUrl).pipe(
      tap(data => console.log('All ' + JSON.stringify(data))),
        catchError(this.handleError)
    );
  }

  private handleError(err : HttpErrorResponse){
    let errorMessage = '';

    if (err.error instanceof  ErrorEvent){
      errorMessage = `An error occurred: ${err.error.message}`;

    } else {
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
