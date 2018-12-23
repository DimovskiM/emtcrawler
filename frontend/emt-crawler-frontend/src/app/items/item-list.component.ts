import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {Item} from "./item";
import {ItemService} from "./item.service";



@Component({
  selector : 'pm-items',
  templateUrl : './item-list.component.html',
  styleUrls : ['./item-list.component.css']
})
export class ItemListComponent implements OnInit{
  currentPage:number =  1;
  previousLabel:string = "Назад";
  nextLabel:string = "Напред";



  pageTitle: string ='Проект по Електронска и Мобилна Трговија - Изработен од Михајло Димовски и Кристијан Габер';

  imageWidth: number = 50;
  imageMargin:number = 2;


  errorMessage : string;

  _listFilter:string;
  items:Item [];
  filteredItems:Item[];

  @Output() pageChange : EventEmitter<number>;

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
