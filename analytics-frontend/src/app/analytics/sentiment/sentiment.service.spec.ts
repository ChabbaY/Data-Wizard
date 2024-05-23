import { TestBed } from '@angular/core/testing';
import { SentimentService } from './sentiment.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('SentimentService', () => {
  let service: SentimentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
    service = TestBed.inject(SentimentService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
