import { Component, OnInit } from '@angular/core';
import { CommonService } from '../services/common.service';
import { Product } from '../form/product'

@Component({
  selector: 'app-seller-inventory',
  templateUrl: './seller-inventory.component.html',
  styleUrls: ['./seller-inventory.component.css']
})
export class SellerInventoryComponent implements OnInit {
  products: any[];
  item: Product = new Product();
  isSuccessful: boolean = false;

  constructor(private commonService: CommonService) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts(){
    this.commonService.getProductsByUser().subscribe(
      data => {
        this.products = data;
        console.log(JSON.stringify(this.products));
      },
      err => {
      }
    );
  }

  addStock(){
    this.commonService.registerProducts(this.item).subscribe(
      data => {
        this.isSuccessful = true;
        this.getProducts();
      },
      err => {
        this.isSuccessful = false;
      }
    )
  }

}
