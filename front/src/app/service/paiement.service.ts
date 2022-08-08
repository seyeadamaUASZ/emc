import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Payeur} from "../models/Payeur";
import {ResponseApi} from "../models/ResponseApi";

@Injectable({
  providedIn: 'root'
})
export class PaiementService {

  api = environment.api;


  constructor(private http: HttpClient) {
  }

  getListPaiement() {
    return this.http.get<any>(this.api + 'paiement/list');
  }

  getListPaiementByUser(username: any) {
    return this.http.get<any>(this.api + 'paiement/list/' + username);
  }

  getPaiementByReferencePaiement(reference: any) {
    return this.http.get<any>(this.api + 'paiement/referencePaiement/' + reference);
  }

//operateur de paiement
  getOperateurPaiement() {
    return this.http.get<any>(this.api + 'operateurPaiement/list');
  }

  CreateOperateurPaiement(operateurPaiement: any) {
    return this.http.post<any>(this.api + 'operateurPaiement/create', operateurPaiement);
  }

  payer(objet: Payeur) {
    const headers = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    return this.http.post<any>(this.api + 'moyenPaiement/crypter', objet, headers);
  }

  getNotification(objet: any) {
    return this.http.post<any>(this.api + 'paiement/notification', objet);
  }
}
