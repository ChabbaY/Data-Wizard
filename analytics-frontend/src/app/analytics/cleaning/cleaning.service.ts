import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CleaningService {
  clean(value: string): string {
    value = value.toLowerCase();
    value = value.trim()

    let result: string = "";
    for (let i: number = 0; i < value.length; i++) {
      let char: string = value.substring(i, i + 1);
      switch (char) {
        case "ä":
          result = result.concat("ae");
          break;
        case "ö":
          result = result.concat("oe");
          break;
        case "ü":
          result = result.concat("ue");
          break;
        case "ß":
          result = result.concat("ss");
          break;
        default:
          result = result.concat(char);
      }
    }

    return result;
  }
}
