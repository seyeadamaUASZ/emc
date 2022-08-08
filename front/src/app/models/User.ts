export class User {

  constructor(

    private username:string,

    private accessToken: string,

    private authorities: any,

    private refreshTokenToken: string,

    private expirationDate: Date

  ) {}



  get expireDate() {

    return this.expirationDate;

  }



  get getUserToken() {

    return this.accessToken;

  }



  get getAuthorities() {

    return this.authorities;

  }



  get getUserRefreshToken() {

    return this.refreshTokenToken;

  }



  get getUsername() {

    return this.username;

  }



  set setexpireDate(value: any) {

    this.expirationDate =value;

  }



  set setUserToken(value: any) {

    this.accessToken = value;

  }



  set setAuthorities(value: any) {

    this.authorities = value;

  }



  set setUserRefreshToken(value: any) {

    this.refreshTokenToken = value;

  }



  set setUsername(value: any) {

    this.refreshTokenToken = value;

  }



}
