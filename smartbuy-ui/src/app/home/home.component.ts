import { Component, OnInit } from '@angular/core';
import { Categories } from '../form/category';
import { CommonService } from '../services/common.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  categories: any;
  products: any[];
  buyer: boolean = false;
  constructor(private commonService: CommonService, private tokenService: TokenStorageService) { }

  ngOnInit() {
    let cat = Object.keys(Categories);
    this.categories = cat.slice(cat.length / 2);

    this.commonService.getAllProducts().subscribe(
      data => {
        this.products = data;
        let roles = this.tokenService.getUser().roles;
        this.buyer = roles.includes('BUYER');
      },
      err => {
      }
    );
  }

  getProductsByCategory(category){
    this.commonService.getProductsByCatgory(category).subscribe(
      data => {
        this.products = data;
      },
      err => {
      }
    );
  }

  addtoCart(product){
    this.commonService.addtoCart(product.id).subscribe(
      data => {
        product.cart = true;
      },
      err => {
      }
    );
  }

  removeFrom(product){
    this.commonService.removeitemFromCart(product.id).subscribe(
      data => {
        this.products = data;
        product.cart = false;
      },
      err => {
      }
    );
  }
}
