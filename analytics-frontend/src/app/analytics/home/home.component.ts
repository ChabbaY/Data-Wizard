import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {TableService} from "../../table.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NgForOf],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {
  rows: string[][] = []; // table row that contains columns
  row_count: number = 0;
  column_titles: string[] = []; // table headers

  constructor(private tableService: TableService) { }

  ngOnInit(): void {
    this.rows = this.tableService.get_rows();
    this.row_count = this.tableService.get_row_count();
    this.column_titles = this.tableService.get_column_titles();
  }

  ngOnDestroy() {
    this.tableService.set_rows(this.rows);
    this.tableService.set_row_count(this.row_count);
    this.tableService.set_column_titles(this.column_titles);
  }

  init() {
    this.read_from_csv("A;B\nC;D", ";", true);
  }

  set_titles(values: string[]): void {
    this.column_titles = values;
  }

  add_row(values: string[]): void {
    this.rows.push(values);
    this.row_count++;
  }
  clear_rows(): void {
    this.rows = [];
    this.row_count = 0;
  }

  add_column(title: string): void {
    this.column_titles.push(title);
    for (let i: number = 0; i < this.row_count; i++) {
      this.rows[i].push("");
    }
  }

  read_from_csv(csv: string, delimiter: string, header: boolean): void {
    let lines: string[] = csv.split("\n");
    if (lines.length > 0) {
      let values: string[] = lines[0].split(delimiter);
      this.set_titles(values);
      if (!header) {
        this.add_row(values);
      }
    }
    for (let i: number = 1; i < lines.length; i++) {
      let values: string[] = lines[i].split(delimiter);
      this.add_row(values);
    }
  }
  write_to_csv(delimiter: string, include_header: boolean): string {
    let result: string = (include_header) ? this.column_titles.join(delimiter).concat("\n") : "";
    for (let i: number = 0; i < this.row_count; i++) {
      result.concat(this.rows[i].join(delimiter).concat("\n"));
    }
    return result;
  }
}
