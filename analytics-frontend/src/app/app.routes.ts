import { Routes } from '@angular/router';
import {HomeComponent} from "./analytics/home/home.component";
import {ClassificationComponent} from "./analytics/classification/classification.component";
import {PatternsComponent} from "./analytics/patterns/patterns.component";
import {SentimentComponent} from "./analytics/sentiment/sentiment.component";
import {VisualizationComponent} from "./analytics/visualization/visualization.component";

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'classification', component: ClassificationComponent},
  { path: 'patterns', component: PatternsComponent},
  { path: 'sentiment', component: SentimentComponent},
  { path: 'visualization', component: VisualizationComponent},
  { path: '**', redirectTo: 'home'},
];
