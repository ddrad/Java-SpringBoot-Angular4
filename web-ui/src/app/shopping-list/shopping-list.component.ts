import {Component, OnDestroy, OnInit} from '@angular/core';
import {Order} from '../common/model/order.model';
import {ShoppingListService} from './shopping-list.service';
import {Subscription} from 'rxjs/Subscription';
import {AuthService} from '../auth/auth.service';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit, OnDestroy {


  private subscription: Subscription;
  orders: Order[];

  constructor(private shoppingListService: ShoppingListService, private auth: AuthService) {
  }

  ngOnInit() {
    this.auth.isAuthenticared();
    this.orders = this.shoppingListService.getOrders();

    this.subscription = this.shoppingListService.orderChanged.subscribe(
      (orders: Order[]) => {
        this.orders = orders;
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
