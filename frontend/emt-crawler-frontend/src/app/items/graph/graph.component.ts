import {Component, OnInit,Input} from "@angular/core";
import {IPriceData} from "../IPriceData";
import {Item} from "../item";

@Component({
  selector: 'pm-graph',
  templateUrl: './graph.component.html'
})
export class GraphComponent implements OnInit{

  @Input() item:Item;
   graphType: string = 'line';

   @Input() graphData: Array<IPriceData>;

  graphItems: Array<any>;
  graphLabels :Array<any>;

  items:number[];
  labels:any[];

  ngOnInit(): void {
    this.getChartOptions();
    this.generateData();
  }


  getChartOptions():void{
    this.labels = [];
    this.graphLabels = new Array<any>();
    this.graphData.forEach(data => {
      this.labels.push(data.date);
    });

    this.labels.push(this.item.date);
  }

  public lineChartOptions:any = {
    responsive: true
  };
  public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  generateData():void{

  this.items = [];
  this.graphItems = new  Array<any>();

    this.graphData.forEach(prices => {
      this.items.push(prices.price);
    });
    this.items.push(this.item.price);
    this.graphItems.push({data:this.items,label:"Цена"});
  }
  public lineChartLegend:boolean = true;

   // description: string = this.item.name;
}
