import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {ItemListComponent} from "./items/item-list.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
