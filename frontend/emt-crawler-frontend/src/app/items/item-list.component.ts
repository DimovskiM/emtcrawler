import {Component, OnInit} from "@angular/core";
import {Item} from "./item";
import {ItemService} from "./item.service";



@Component({
  selector : 'pm-items',
  templateUrl : './item-list.component.html',
  styleUrls : ['./item-list.component.css']
})
export class ItemListComponent implements OnInit{
pageTitle: string ='EMT Project by Mihajlo Dimovski & Kristijan Gaber';
imageWidth: number = 50;
imageMargin:number = 2;
errorMessage : string;
  _listFilter:string;
  items:Item [];
  filteredItems:Item[];

  constructor(private itemService : ItemService){

  }

  get listFilter():string{
    return this._listFilter;
  }

  set listFilter(value : string){
    this._listFilter = value;
    this .filteredItems = this.listFilter ? this.performFilter(this.listFilter):this.items;
  }

  performFilter(filterBy:string){
    filterBy = filterBy.toLocaleLowerCase();
    return this.items.filter((item : Item) =>
                      item.name.toLocaleLowerCase().indexOf(filterBy)!== -1);
  }

  ngOnInit(): void {
    this.itemService.getItems().subscribe(items =>{ this.items = items;
    this.filteredItems = this.items;

      },
      error => this.errorMessage = <any> error);
    this.filteredItems = this.items;
  }


}
