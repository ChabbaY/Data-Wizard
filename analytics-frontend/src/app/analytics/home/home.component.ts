import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {TableService} from "../../table.service";
import {FormsModule} from "@angular/forms";
import {ValidationService} from "../validation.service";
import {ClassificationService} from "../classification/classification.service";
import {SentimentService} from "../sentiment/sentiment.service";
import {Subscription} from "rxjs";
import {CleaningService} from "../cleaning/cleaning.service";

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

  methods: string[] = ["Sprache", "Kategorie", "Stimmung"];
  method: string = "Sprache";

  private subs: Subscription[] = [];

  constructor(private tableService: TableService, private validationService: ValidationService,
              private classificationService: ClassificationService, private sentimentService: SentimentService,
              private cleaningService: CleaningService, private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.rows = this.tableService.get_rows();
    this.row_count = this.tableService.get_row_count();
    this.column_titles = this.tableService.get_column_titles();
  }

  ngOnDestroy(): void {
    this.tableService.set_rows(this.rows);
    this.tableService.set_row_count(this.row_count);
    this.tableService.set_column_titles(this.column_titles);
    this.subs.forEach((sub: Subscription): void => {
      sub.unsubscribe();
    });
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

  add_column(title: string): number {
    let length: number = this.column_titles.length
    this.column_titles.push(title);
    for (let i: number = 0; i < this.row_count; i++) {
      this.rows[i].push("");
    }
    return length;
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
      if (lines[i] == "") continue;
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

    if (file) {
      const reader = new FileReader();

      reader.onload = (e: any) => {
        this.file = e.target.result;
        this.uploaded = true;
        this.upload_event = event;
      }

      reader.onerror = (e) => {
        console.error("File could not be read: " + e.target!.error!.message);
      }

      //reader.readAsText(file, 'UTF-8');
      reader.readAsText(file, 'iso-8859-1');
    }
  }

  downloadFile(): void {
    let file_content: string = this.write_to_csv(this.delimiter, this.has_header);

    const blob: Blob = new Blob([file_content], { type: 'text/csv;charset=utf-8;' });

    let hiddenElement: HTMLAnchorElement = document.createElement('a');

    const url: string = URL.createObjectURL(blob);
    hiddenElement.href = url;
    hiddenElement.target = '_blank';
    hiddenElement.download = 'export.csv';
    hiddenElement.click();

    URL.revokeObjectURL(url);
  }

  enrich(srcIndex: number): void {
    let tarIndex: number;
    switch(this.method) {
      case "Sprache":
        tarIndex = this.add_column("Sprache");
        for (let i = 0; i < this.row_count; i++) {
          this.subs.push(this.validationService.validateLanguage(this.rows[i][srcIndex]).subscribe(
            (response: string): void => {
              this.rows[i][tarIndex] = response;
              this.changeDetector.markForCheck();
            }
          ));
        }
        break;
      case "Kategorie":
        tarIndex = this.add_column("Kategorie");
        for (let i = 0; i < this.row_count; i++) {
          this.subs.push(this.classificationService.classify(this.rows[i][srcIndex]).subscribe(
            (response: string): void => {
              this.rows[i][tarIndex] = response;
              this.changeDetector.markForCheck();
            }
          ));
        }
        break;
      case "Stimmung":
        tarIndex = this.add_column("Stimmung");
        for (let i = 0; i < this.row_count; i++) {
          this.subs.push(this.sentimentService.classify(this.rows[i][srcIndex]).subscribe(
            (response: string): void => {
              this.rows[i][tarIndex] = response;
              this.changeDetector.markForCheck();
            }
          ));
        }
        break;
    }
  }

  clean(): void {
    for (let row: number = 0; row < this.row_count; row++) {
      for (let column: number = 0; column < this.column_titles.length; column++) {
        this.rows[row][column] = this.cleaningService.clean(this.rows[row][column]);
      }
    }
  }
}
