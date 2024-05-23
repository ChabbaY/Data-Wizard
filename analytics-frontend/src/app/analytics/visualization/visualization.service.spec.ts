import { TestBed } from '@angular/core/testing';
import { VisualizationService } from './visualization.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('VisualizationService', () => {
  let service: VisualizationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(VisualizationService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
