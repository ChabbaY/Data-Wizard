import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ClassificationService} from "./classification.service";

@Component({
  selector: 'app-classification',
  standalone: true,
  templateUrl: './classification.component.html',
  styleUrl: './classification.component.scss'
})
export class ClassificationComponent implements OnInit, OnDestroy {
  private subs: Subscription[] = [];
  public answer: string = "";
  constructor(private classificationService: ClassificationService) { }

  ngOnInit(): void {
    this.subs.push(this.classificationService.test().subscribe(
      (response: string) => {
        this.answer = response;
      },
      (error) => {
        console.log(error);
      }
    ))
  }

  ngOnDestroy(): void {
    this.subs.forEach((sub) => {
      sub.unsubscribe();
    });
  }
}
