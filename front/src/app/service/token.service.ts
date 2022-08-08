import {Injectable} from '@angular/core';
import jwt_decode from "jwt-decode";


const TOKEN_KEY = 'userData';


@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() {
  }

  signOut(): void {
    window.sessionStorage.clear();
    window.localStorage.clear();
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    if (window.localStorage.getItem(TOKEN_KEY) !== null) {
      return window.localStorage.getItem(TOKEN_KEY);
    }
    return null;
  }

  // @ts-ignore
  public getExpirationTime(): number | null {
    if (window.localStorage.getItem(TOKEN_KEY) !== null) {
      // @ts-ignore
      return this.getDecodedAccessToken().exp;
    }

  }


  public getUser(): any {
    if (this.getDecodedAccessToken() != null) {
      const infoDecoded = this.getDecodedAccessToken();
      if (infoDecoded) {
        return infoDecoded.currentUser;
      }
    }


    return {};
  }

  public getMenu(): any {

    if (this.getDecodedAccessToken()) {
      const infoDecoded = this.getDecodedAccessToken();
      if (infoDecoded) {
        return infoDecoded.menu;
      }
    }

    return {};
  }

  getDecodedAccessToken(): any {
    if (this.getToken() !== null) {
      // @ts-ignore
      return jwt_decode(this.getToken());
    }
    return null;
  }

  decodedPassedAccessToken(token: any): any {

    if (this.getToken() !== null) {
      return jwt_decode(token);
    }
    return null;
  }

  public getUserFromPassedtoken(token: any): any {
    if (this.decodedPassedAccessToken(token) != null) {
      const infoDecoded = this.decodedPassedAccessToken(token);
      if (infoDecoded) {
        return infoDecoded.currentUser;
      }
    }


    return {};
  }

  getDecodedResettoken(token: any) {
    return jwt_decode(token);
  }

}
