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
  private subscriptionConfermed: Subscription;
  orders: Order[];
  confirmedOrders: Order[];

  constructor(private shoppingListService: ShoppingListService, private auth: AuthService) {
  }

  ngOnInit() {
    this.orders = this.shoppingListService.getOrders();
    this.confirmedOrders = this.shoppingListService.getConfirmedOrders();

    this.subscription = this.shoppingListService.orderChanged.subscribe(
      (orders: Order[]) => {
        this.orders = orders;
      });

    this.subscriptionConfermed = this.shoppingListService.confirmedOrderChanged.subscribe(
        (orders: Order[]) => {
          this.confirmedOrders = orders;
        });
  }

  onConfirm(order: Order) {
    order.status='CONFIRMED';
    console.log(order);
    this.shoppingListService.addOrderToConfirmedShoppingList(order);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.subscriptionConfermed.unsubscribe();
  }

}
