import { Component, OnInit } from '@angular/core';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  items: any[] = [];
  totalAmount: number = 0;
  orderSuccess: boolean = false;

  constructor(private commonService: CommonService) { }

  ngOnInit() {
    this.items = this.commonService.getCartItems();
    this.items.forEach(item => {
      this.totalAmount = this.totalAmount + item.product.price;
    })
  }

  placeOrder(){
    this.commonService.placeOrder(this.items).subscribe(
      data => {
        this.orderSuccess = true;
      },
      err => {
        this.orderSuccess = false;
      }
    );
  }

}
