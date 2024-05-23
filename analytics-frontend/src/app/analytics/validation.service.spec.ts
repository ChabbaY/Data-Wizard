import { TestBed } from '@angular/core/testing';
import { ValidationService } from './validation.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('ValidationService', () => {
  let service: ValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ValidationService);
  });

  it('should be created', () => {
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
