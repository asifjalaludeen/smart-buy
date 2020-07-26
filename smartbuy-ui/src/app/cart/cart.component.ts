import { Component, OnInit } from '@angular/core';
import { CommonService } from '../services/common.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  items: any[] = [];
  totalAmount: number = 0;
  constructor(private commonService: CommonService, private route: Router) { }

  ngOnInit() {
    this.getItemsonCart();
  }

  getItemsonCart(){
    this.commonService.getProductsOnCart().subscribe(
      data => {
        this.items = data;
        this.items.forEach(item => {
          this.totalAmount = this.totalAmount + item.product.price;
        })
      },
      err => {
      }
    );
  }

  removeFromCart(productId){
    this.commonService.removeitemFromCart(productId).subscribe(
      data => {
        this.getItemsonCart();
      },
      err => {
      }
    );
  }

  checkout(){
    this.commonService.setCartItems(this.items);
    this.route.navigate(['/checkout']);
  }

}
