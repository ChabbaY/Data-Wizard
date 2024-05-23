import { TestBed } from '@angular/core/testing';
import { VisualizationService } from './visualization.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('VisualizationService', () => {
  let service: VisualizationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
    service = TestBed.inject(VisualizationService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
