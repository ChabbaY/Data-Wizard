import { TestBed } from '@angular/core/testing';
import { SentimentService } from './sentiment.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('SentimentService', () => {
  let service: SentimentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(SentimentService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
