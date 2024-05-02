import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClassificationService {

  constructor(private http: HttpClient) { }

  test(): Observable<string> {
    console.log("test")
    return this.http.get<string>('http://localhost:5555/classification?value=Fussball');
  }
}
