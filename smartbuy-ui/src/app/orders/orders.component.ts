
import { Component, OnInit } from '@angular/core';
import { CommonService } from '../services/common.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders: any[] = [];
  seller: boolean = false;
  buyer: boolean = false;
  constructor(private commonService: CommonService, private tokenService: TokenStorageService) { }

  ngOnInit() {
    this.getOrders();
  }

  getOrders(){
    this.commonService.getOrders().subscribe(
      data => {
        let roles = this.tokenService.getUser().roles;
        this.seller = roles.includes('SELLER');
        this.buyer = roles.includes('BUYER');
        this.orders = data;
      },
      err => {

      }
    );
  }

  dispatchOrder(id){
    this.commonService.dispatchOrder(id).subscribe(
      data => {
        this.getOrders();
      },
      err => {

      }
    );
  }

  receiveOrder(id){
    this.commonService.receiveOrder(id).subscribe(
      data => {
        this.getOrders();
      },
      err => {

      }
    );
  }
}
