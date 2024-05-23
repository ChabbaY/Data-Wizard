import { Routes } from '@angular/router';
import {HomeComponent} from "./analytics/home/home.component";
import {ClassificationComponent} from "./analytics/classification/classification.component";
import {ValidationComponent} from "./analytics/validation/validation.component";
import {SentimentComponent} from "./analytics/sentiment/sentiment.component";
import {VisualizationComponent} from "./analytics/visualization/visualization.component";

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'validation', component: ValidationComponent},
  { path: 'classification', component: ClassificationComponent},
  { path: 'sentiment', component: SentimentComponent},
  { path: 'visualization', component: VisualizationComponent},
  { path: '**', redirectTo: 'home'},
];
