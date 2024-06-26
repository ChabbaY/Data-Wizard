import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Validation } from "./validation.model";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor(private http: HttpClient) { }

  validateInt(value: string): Observable<Validation> {
    return this.http.get<Validation>(`http://localhost:5555/validation/int?value=${encodeURI(value)}`);
  }
  validateDouble(value: string): Observable<Validation> {
    return this.http.get<Validation>(`http://localhost:5555/validation/double?value=${encodeURI(value)}`);
  }
  validateYear(value: string): Observable<Validation> {
    return this.http.get<Validation>(`http://localhost:5555/validation/year?value=${encodeURI(value)}`);
  }
  validateDate(value: string): Observable<Validation> {
    return this.http.get<Validation>(`http://localhost:5555/validation/date?value=${encodeURI(value)}`);
  }
  validateLongLat(value: string): Observable<Validation> {
    return this.http.get<Validation>(`http://localhost:5555/validation/long-lat?value=${encodeURI(value)}`);
  }
  validateLanguage(value: string): Observable<string> {
    return this.http.get<string>(`http://localhost:5555/validation/language?value=${encodeURI(value)}`);
  }
}
