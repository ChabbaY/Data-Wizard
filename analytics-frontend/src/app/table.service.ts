import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TableService {
  private rows: string[][] = []; // table row that contains columns
  private row_count: number = 0;
  private column_titles: string[] = []; // table headers

  constructor() { }

  set_rows(rows: string[][]): void {
    this.rows = rows;
  }
  get_rows(): string[][] {
    return this.rows;
  }
  set_row_count(row_count: number): void {
    this.row_count = row_count;
  }
  get_row_count(): number {
    return this.row_count;
  }
  set_column_titles(column_titles: string[]): void {
    this.column_titles = column_titles;
  }
  get_column_titles(): string[] {
    return this.column_titles;
  }
}
