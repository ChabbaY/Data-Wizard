import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { ClassificationComponent } from './classification/classification.component';
import { PatternsComponent } from './patterns/patterns.component';
import { SentimentComponent } from './sentiment/sentiment.component';
import { VisualizationComponent } from './visualization/visualization.component';

@NgModule({
  declarations: [
    HomeComponent,
    ClassificationComponent,
    PatternsComponent,
    SentimentComponent,
    VisualizationComponent
  ],
  imports: [
    CommonModule
  ]
})
export class AnalyticsModule { }
