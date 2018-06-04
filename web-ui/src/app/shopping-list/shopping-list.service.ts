import { Order } from "../common/model/order.model";
import { Subject } from "rxjs/Subject";

export class ShoppingListService {

  orderChanged = new Subject<Order[]>();
  confirmedOrderChanged = new Subject<Order[]>();

  orders: Order[] = [];
  confirmedOrders: Order[] = [];

  getOrders() {
    return this.orders.slice();
  }

  getConfirmedOrders() {
    return this.confirmedOrders.slice();
  }

  addOrderToShoppingList(order: Order) {
    this.orders.push(order);
    this.orderChanged.next(this.orders.slice())
  }

  addOrdersToShoppingList(orders: Order[]) {
    this.orders.push(...orders);
    this.orderChanged.next(this.orders.slice());
  }

  addOrderToConfirmedShoppingList(order: Order) {
    this.remove(this.orders, order);
    this.confirmedOrders.push(order);
    this.confirmedOrderChanged.next(this.confirmedOrders.slice())
  }

  private remove(array, element): Order[] {
    this.orders = array.filter(e => { console.log(e === element); return e !== element; });
    this.orderChanged.next(this.orders.slice());
    return this.orders;
  }
}
