import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SentimentService {

  constructor(private http: HttpClient) { }

  classify(value: string): Observable<string> {
    return this.http.get<string>(`http://localhost:5555/classification/sentiment?value=${encodeURI(value)}`);
  }

  classifyByWordlist(value: string): Observable<string> {
    return this.http.get<string>(`http://localhost:5555/classification/sentiment-wordlist?value=${encodeURI(value)}`);
  }
}
