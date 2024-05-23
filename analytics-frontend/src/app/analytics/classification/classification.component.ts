import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs";
import { ClassificationService } from "./classification.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-classification',
  standalone: true,
  templateUrl: './classification.component.html',
  imports: [
    FormsModule
  ],
  styleUrl: './classification.component.scss'
})
export class ClassificationComponent implements OnInit, OnDestroy {
  private subs: Subscription[] = [];
  value: string = "";
  answer: string = "";
  constructor(private classificationService: ClassificationService) { }

  ngOnInit(): void {

  }

  change(): void {
    this.subs.push(this.classificationService.classify(this.value).subscribe(
      (response: string): void => {
        this.answer = response;
      }
    ));
  }

  ngOnDestroy(): void {
    this.subs.forEach((sub: Subscription): void => {
      sub.unsubscribe();
    });
  }
}
