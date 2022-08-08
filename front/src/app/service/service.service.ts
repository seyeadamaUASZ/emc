import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";
import {ResponseApi} from "../models/ResponseApi";
import {Observable} from "rxjs";
import {Utilisateur} from '../models/Utisateur';
import {Marchandise} from "../models/Marchandise";
import {HttpRequestHelper} from "../models/httprequest.helper";

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  api = environment.api;

  constructor(private http: HttpClient, private reqHelper: HttpRequestHelper
  ) {
  }

  marchandises: Marchandise[] = []

  addMarchandise(id: any, origine: string, description: string) {
    if (origin) {
      const marchandise: Marchandise = {'description': description, 'origine': origine,}
      this.marchandises.push(marchandise)

    }
  }

  createDemande(formData: FormData) {
    /*formData.append('document_2', document_2);
    formData.append('document_3', document_3);
    formData.append('document_4', document_4);
    formData.append('document_5', document_5);*/
    return this.http.post<ResponseApi>(this.api + 'importation/create', formData)
  }

  getFiles(): Observable<any> {
    return this.http.get(this.api + 'files');
  }

  getInfoByCodePPM(codePPM: string): Observable<ResponseApi> {
    return this.http.get<ResponseApi>(this.api + 'importation/infoppm/' + codePPM)
  }

  getAllImportations() {
    return this.http.get<ResponseApi>(this.api + "/importation/importations");
  }

  getImportationByNumero(numero: string | null) {
    return this.http.get<ResponseApi>(this.api + "importation/importationParNumero/" + numero);
  }

  getImportationByNumeroAndMail(numero: string, email: String) {
    return this.http.get<ResponseApi>(this.api + "importation/importationByNumeroAndEmail/" + numero + "/" + email);
  }

  getImportationByUser(id: number) {
    return this.http.get<ResponseApi>(this.api + "importation/importationByUser/" + id);
  }

  getStatistiqueByUser(id: number) {
    return this.http.get<ResponseApi>(this.api + "importation/statistique/" + id);
  }

  getUserByMail(mail: string): Observable<ResponseApi> {
    return this.http.get<ResponseApi>(this.api + "importation/utilisateurByMail/" + mail);
  }

  getCodeppm(codeppm: string): Observable<ResponseApi> {
    return this.http.get<ResponseApi>(this.api + "importation/infoppm/" + codeppm);
  }

  authentification(user: Utilisateur) {
    return this.http.post<ResponseApi>(this.api + "auth/login", user);
  }

  inscriptionTemporaire(formData: FormData) {
    return this.http.post<ResponseApi>(this.api + 'auth/inscription', formData)
  }

  inscription(formData: FormData) {
    return this.http.post<ResponseApi>(this.api + 'auth/user/inscription', formData)
  }

  updateImportation(numero: string | null) {
    return this.http.get<ResponseApi>(this.api + "importation/update/" + numero);
  }

  demandePdf(numero: string) {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json',
      'headers': this.reqHelper.getReqOptions("genererRapportPdf_rapport")
    };
    return this.http.get<any>(this.api + 'importation/fichierVisualiser/pdf/' + numero, httpOptions);
  }

  demandeQrCode(numero: string) {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json',
      'headers': this.reqHelper.getReqOptions("genererRapportPdf_rapport")
    };
    return this.http.get<any>(this.api + 'importation/fichierQrCode/pdf/' + numero, httpOptions);
  }

  downloadfichierPdf(numero: any) {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json',
      'headers': this.reqHelper.getReqOptions("genererRapportPdf_rapport")
    };
    return this.http.get<any>(this.api + 'importation/downloadFile/' + numero, httpOptions);
  }

}
