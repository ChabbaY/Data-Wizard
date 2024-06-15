import { Routes } from '@angular/router';
import {HomeComponent} from "./analytics/home/home.component";
import {ClassificationComponent} from "./analytics/classification/classification.component";
import {ValidationComponent} from "./analytics/validation/validation.component";
import {SentimentComponent} from "./analytics/sentiment/sentiment.component";
import {CleaningComponent} from "./analytics/cleaning/cleaning.component";

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'validation', component: ValidationComponent},
  { path: 'classification', component: ClassificationComponent},
  { path: 'sentiment', component: SentimentComponent},
  { path: 'cleaning', component: CleaningComponent},
  { path: '**', redirectTo: 'home'},
];
