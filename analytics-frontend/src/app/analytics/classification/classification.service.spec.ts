import { TestBed } from '@angular/core/testing';
import { ClassificationService } from './classification.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ClassificationService', () => {
  let service: ClassificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
    service = TestBed.inject(ClassificationService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
