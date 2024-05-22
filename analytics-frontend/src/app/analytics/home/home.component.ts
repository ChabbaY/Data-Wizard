import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {TableService} from "../../table.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NgForOf, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {
  rows: string[][] = []; // table row that contains columns
  row_count: number = 0;
  column_titles: string[] = []; // table headers

  delimiter: string = ";";
  has_header: boolean = true;
  uploaded: boolean = false;
  file: string = "";
  upload_event: any;

  constructor(private tableService: TableService) { }

  ngOnInit(): void {
    this.rows = this.tableService.get_rows();
    this.row_count = this.tableService.get_row_count();
    this.column_titles = this.tableService.get_column_titles();
  }

  ngOnDestroy(): void {
    this.tableService.set_rows(this.rows);
    this.tableService.set_row_count(this.row_count);
    this.tableService.set_column_titles(this.column_titles);
  }

  init(): void {
    this.read_from_csv(this.file, this.delimiter, this.has_header);
    this.upload_event.target.value = null; // reset selected file
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
    let lines: string[] = csv.split("\r\n");
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
    let result: string = (include_header) ? this.column_titles.join(delimiter).concat("\r\n") : "";
    for (let i: number = 0; i < this.row_count; i++) {
      result = result.concat(this.rows[i].join(delimiter).concat("\r\n"));
    }
    return result;
  }

  async uploadFile(event: any): Promise<void> {
    this.uploaded = false;
    const file = event.target.files[0];
    this.file = await file.text();
    this.uploaded = true;
    this.upload_event = event;
  }

  downloadFile(): void {
    let file_content: string = this.write_to_csv(this.delimiter, this.has_header);
    let hiddenElement: HTMLAnchorElement = document.createElement('a');
    hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(file_content);
    hiddenElement.target = '_blank';
    hiddenElement.download = 'data.csv';
    hiddenElement.click();
  }
}
