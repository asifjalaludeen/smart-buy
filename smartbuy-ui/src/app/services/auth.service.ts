import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../form/user';

const AUTH_API = 'https://smart-buy-lb-1890780090.us-east-1.elb.amazonaws.com/smartbuy/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

    login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user: User): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      username: user.username,
      emailId: user.emailId,
      password: user.password,
      phoneNumber: user.phoneNumber,
      role: user.role,
      name: user.name
    }, httpOptions);
  }
}
