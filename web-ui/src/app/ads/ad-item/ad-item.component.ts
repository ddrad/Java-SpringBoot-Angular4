import {Component, Input, OnInit} from '@angular/core';
import {Ad} from '../ad.model';

@Component({
  selector: 'app-product-item',
  templateUrl: './ad-item.component.html',
  styleUrls: ['./ad-item.component.css']
})
export class AdItemComponent implements OnInit {

  @Input() ad: Ad;
  @Input() index: number;

  constructor() { }

  ngOnInit() {
  }

}
