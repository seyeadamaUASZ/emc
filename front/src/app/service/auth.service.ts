import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import jwtDecode from 'jwt-decode';
import { Router } from '@angular/router';
import {environment} from "../../environments/environment";
import {ResponseApi} from "../models/ResponseApi";
import {TokenService} from "./token.service";
import {AuthResponseData} from "../models/AuthResponseData";
import {User} from "../models/User";
@Injectable({
  providedIn: 'root'
})
export class AuthService {



  BASE_URL = `${environment.api}auth`;

  timeoutInterval: any;
  constructor(
    private http: HttpClient,
    private _tokenService:TokenService,
    private route:Router
  ) {}



  login(email: string, password: string): Observable<ResponseApi> {
    const url = `${this.BASE_URL}/login`;
    return this.http.post<ResponseApi>(
      url,
      { email, password, returnSecureToken: true }
    );
  }

  getErrorMessage(message: string) {
    switch (message) {
      case 'WRONG_CREDENTIALS':
        return 'Email ou mot de passe incorrect';
      case 'FIRST_CONNECTION':
        return 'Veuillez creer un mot de passe';
      case 'DISABLED_ACCOUNT':
        return 'votre compte est desactive';
      case 'OK':
        return 'success';
      case 'TOO_MANY_ATTEMPTS_TRY_LATER':
        return 'Many attempts, try later';
      default:
        return 'Problème de connexion au serveur';
    }
  }


  formatUser(data: AuthResponseData){
    const refToken: any = jwtDecode(data?.accessToken);
    const expirationDate = new Date(refToken.exp * 1000)
    const user = new User(data?.username, data?.accessToken, data?.authorities, data?.refreshToken, expirationDate);
    return user;
  }

/*
  runTimeoutInterval(user: User){
    const todaysDate = new Date().getTime();
    const expirationDate = user.expireDate.getTime();
    const timeInterval = expirationDate - todaysDate;
    this.clearTimeoutInterval();

    this.timeoutInterval = setTimeout(() => {
      console.log('in runtime');
      //logout functionnality or get the refresh token
      this.store.dispatch(autoLogout());
    },  timeInterval);
  }
*/

  clearTimeoutInterval() {
    if (this.timeoutInterval) {
      //alert("clear")
      clearInterval(this.timeoutInterval);
      clearTimeout(this.timeoutInterval);
      this.timeoutInterval = null;
    }
  }

  getUserFromLocalStorage() {
    const userDataString = localStorage.getItem('userData');
    if (userDataString){
      const userData = JSON.parse(userDataString);
      const expirationdate = new Date(userData.expirationDate);
      const user = new User(userData.username, userData.accessToken, userData.authorities, userData.refreshTokenToken, expirationdate);
      return user;
    }
    return null;
  }


  logout() {
    localStorage.removeItem('userData');
    this.NavigateToLogin();
  }

  /*setAuserInLocalStorage(user: User) {
    localStorage.setItem('userData', JSON.stringify(user));
    this.runTimeoutInterval(user);

  }*/


  getmenuUser(){
    return this._tokenService.getDecodedAccessToken().menu;
  }

  getCurrentUser(){
    if(this._tokenService.getDecodedAccessToken() != null)
      return this._tokenService.getDecodedAccessToken().currentUser;
    else
      return null;
  }

  NavigateToLogin(){
    this.route.navigate(['/auth/login']);
  }





}
