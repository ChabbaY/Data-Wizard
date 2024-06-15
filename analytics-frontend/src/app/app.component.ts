import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {NgForOf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, NgForOf, NgOptimizedImage],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'analytics-frontend';

  home = { name: "Daten anreichern", target: "" };
  items = [
    { name: "Validierung", target: "validation" },
    { name: "Klassifizierung", target: "classification" },
    { name: "Sentiment Analyse", target: "sentiment" },
    { name: "Bereinigung", target: "cleaning" }
  ];
}
