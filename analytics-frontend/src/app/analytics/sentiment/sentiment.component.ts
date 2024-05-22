import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Subscription} from "rxjs";
import {SentimentService} from "./sentiment.service";

@Component({
  selector: 'app-sentiment',
  standalone: true,
  templateUrl: './sentiment.component.html',
  imports: [
    FormsModule
  ],
  styleUrl: './sentiment.component.scss'
})
export class SentimentComponent implements OnInit, OnDestroy {
  private subs: Subscription[] = [];
  value: string = "";
  answer: string = "";
  answer_wordlist: string = "";
  constructor(private sentimentService: SentimentService) { }

  ngOnInit(): void {

  }

  change(): void {
    this.subs.push(this.sentimentService.classify(this.value).subscribe(
      (response: string): void => {
        this.answer = response;
      }
    ));
    this.subs.push(this.sentimentService.classifyByWordlist(this.value).subscribe(
      (response: string): void => {
        this.answer_wordlist = response;
      }
    ));
  }

  ngOnDestroy(): void {
    this.subs.forEach((sub: Subscription): void => {
      sub.unsubscribe();
    });
  }
}
