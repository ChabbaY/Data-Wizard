import { TestBed } from '@angular/core/testing';
import { PatternsService } from './patterns.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('PatternsService', () => {
  let service: PatternsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(PatternsService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
