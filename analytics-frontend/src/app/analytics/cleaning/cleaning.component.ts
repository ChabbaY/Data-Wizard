import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {CleaningService} from "./cleaning.service";

@Component({
  selector: 'app-visualization',
  standalone: true,
  templateUrl: './cleaning.component.html',
  imports: [
    FormsModule
  ],
  styleUrl: './cleaning.component.scss'
})
export class CleaningComponent {
  value: string = "";
  answer: string = "";

  constructor(private cleaningService: CleaningService) {}

  change() {
    this.answer = this.cleaningService.clean(this.value);
  }
}
