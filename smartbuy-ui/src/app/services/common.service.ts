import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../form/product';

const BASE_URL = 'https://smart-buy-lb-1890780090.us-east-1.elb.amazonaws.com/smartbuy/api';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  cartItems: any[];
  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any> {
    return this.http.get(BASE_URL + '/product/all');
  }

  registerProducts(product: Product): Observable<any> {
    return this.http.post(BASE_URL + '/product/register', product, httpOptions);
  }

  getProductsByCatgory(category: string): Observable<any> {
    return this.http.get(BASE_URL + '/product/all?findBy=category&category='+category);
  }

  getProductsByUser(): Observable<any> {
    return this.http.get(BASE_URL + '/product/all?findBy=user');
  }

  addtoCart(productId: number): Observable<any> {
    return this.http.post(BASE_URL + '/cart/add/'+productId,'');
  }

  getProductsOnCart(): Observable<any> {
    return this.http.get(BASE_URL + '/cart');
  }

  removeitemFromCart(productId: number): Observable<any> {
    return this.http.delete(BASE_URL + '/cart/remove/'+productId);
  }

  setCartItems(cartItems): void{
    this.cartItems = cartItems;
  }

  getCartItems(): any{
    return this.cartItems
  }

  placeOrder(items: any): Observable<any> {
    return this.http.post(BASE_URL + '/order/place',{cartItems :items});
  }

  getOrders(): Observable<any> {
    return this.http.get(BASE_URL + '/order');
  }


  dispatchOrder(orderid): Observable<any> {
    return this.http.put(BASE_URL + '/order/dispatch/'+orderid, "");
  }

  receiveOrder(orderid): Observable<any> {
    return this.http.put(BASE_URL + '/order/receive/'+orderid, "");
  }
}
