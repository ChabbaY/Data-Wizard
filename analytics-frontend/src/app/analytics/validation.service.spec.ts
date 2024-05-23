import { TestBed } from '@angular/core/testing';
import { ValidationService } from './validation.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ValidationService', () => {
  let service: ValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
    service = TestBed.inject(ValidationService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
