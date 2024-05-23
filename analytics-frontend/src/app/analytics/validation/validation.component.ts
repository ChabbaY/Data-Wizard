import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs";
import { ValidationService } from "../validation.service";
import { Validation } from "../validation.model";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgClass } from "@angular/common";

@Component({
  selector: 'app-validation',
  standalone: true,
  templateUrl: './validation.component.html',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgClass
  ],
  styleUrl: './validation.component.scss'
})
export class ValidationComponent implements OnInit, OnDestroy {
  private subs: Subscription[] = [];
  value: string = "";
  isInt: boolean = false;
  isDouble: boolean = false;
  isYear: boolean = false;
  isDate: boolean = false;
  isLongLat: boolean = false;

  valueLanguage: string = "";
  isLanguage: string = "";
  constructor(private validationService: ValidationService) { }

  ngOnInit(): void {

  }

  validate(): void {
    this.validateInt();
    this.validateDouble();
    this.validateYear();
    this.validateDate();
    this.validateLongLat();
  }
  validateInt(): void {
    this.subs.push(this.validationService.validateInt(this.value).subscribe(
      (response: Validation): void => {
        this.isInt = response.isValid;
      }
    ));
  }
  validateDouble(): void {
    this.subs.push(this.validationService.validateDouble(this.value).subscribe(
      (response: Validation): void => {
        this.isDouble = response.isValid;
      }
    ));
  }
  validateYear(): void {
    this.subs.push(this.validationService.validateYear(this.value).subscribe(
      (response: Validation): void => {
        this.isYear = response.isValid;
      }
    ));
  }
  validateDate(): void {
    this.subs.push(this.validationService.validateDate(this.value).subscribe(
      (response: Validation): void => {
        this.isDate = response.isValid;
      }
    ));
  }
  validateLongLat(): void {
    this.subs.push(this.validationService.validateLongLat(this.value).subscribe(
      (response: Validation): void => {
        this.isLongLat = response.isValid;
      }
    ));
  }
  validateLanguage(): void {
    this.subs.push(this.validationService.validateLanguage(this.valueLanguage).subscribe(
      (response: string): void => {
        this.isLanguage = response;
      }
    ));
  }

  ngOnDestroy(): void {
    this.subs.forEach((sub: Subscription): void => {
      sub.unsubscribe();
    });
  }
}
