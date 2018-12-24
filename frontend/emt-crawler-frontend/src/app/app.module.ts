import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgxPaginationModule} from 'ngx-pagination'
import {GoogleChartsModule} from 'angular-google-charts';



import { AppComponent } from './app.component';
import {ItemListComponent} from "./items/item-list.component";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {GraphComponent} from "./items/graph/graph.component";

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    GraphComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    GoogleChartsModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
