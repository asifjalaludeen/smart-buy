import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  private roles: string[];
  isLoggedIn = false;
  username: string;
  showInventory: boolean;
  showCart: boolean;


  constructor(private tokenStorageService: TokenStorageService) {
    this.tokenStorageService.sessionToken.subscribe(token => {
      this.initPermission();
    });
  }

  ngOnInit() {
    this.initPermission();
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  initPermission(){
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showInventory = this.roles.includes('SELLER');
      this.showCart = this.roles.includes('BUYER');
      this.username = user.username;
    }
  }
}
