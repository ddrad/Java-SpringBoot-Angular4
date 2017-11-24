import {Order} from "../common/model/order.model";
import {Subject} from "rxjs/Subject";

export class ShoppingListService {

  orderChanged = new Subject<Order[]>();

  orders: Order[] = [];

  getOrders() {
    return this.orders.slice();
  }

  addOrderToShoppingList( order: Order) {
    this.orders.push(order);
    this.orderChanged.next(this.orders.slice())
  }

  addOrdersToShoppingList(orders: Order[]) {
    this.orders.push(...orders);
    this.orderChanged.next(this.orders.slice());
  }

}
