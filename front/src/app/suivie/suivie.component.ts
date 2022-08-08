import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {ServiceService} from "../service/service.service";
import {Router} from "@angular/router";
import {PaiementService} from "../service/paiement.service";
import {Payeur} from "../models/Payeur";

@Component({
  selector: 'app-suivie',
  templateUrl: './suivie.component.html',
  styleUrls: ['./suivie.component.css']
})
export class SuivieComponent implements OnInit {


  suivieFormulaire!: FormGroup;

  constructor(private _formBuilder: FormBuilder,
              public dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private service: ServiceService,
              private router: Router,
              private paiementService: PaiementService,
  ) {
  }

  ngOnInit(): void {
    this.suivieFormulaire = this._formBuilder.group({
      NumeroDii: ['Numéro de la DII : ' + this.data.numeroDii, Validators.required],
      Status: ['Status : ' + this.data.statusText, Validators.required],
      DateCreation: ['Date de Création : ' + this.data.dateCreation, Validators.required],
      statusButton: this.data.status,
      numero: this.data.numero,
    });
  }

  closeDialog() {
    this.dialog.closeAll();
  }

  showDemande() {
    localStorage.setItem('numero', this.suivieFormulaire.value.numero)
    this.router.navigate(['demande']);
    this.closeDialog();
  }

  actionPayer() {
    const payeur: Payeur = new Payeur();

    let idFature = localStorage.getItem('idFacture') ?? 0;
    let montant = localStorage.getItem('Montant') ?? '';
    let username = localStorage.getItem('Username') ?? '';
    let prenom = localStorage.getItem('Prenom') ?? '';
    let nom = localStorage.getItem('Nom') ?? '';

    localStorage.clear();

    payeur.idFacture = idFature as number;
    payeur.montantFacture = montant;
    payeur.usernamePayeur = username;
    payeur.nomPayeur = prenom;
    payeur.prenomPayeur = nom;

    this.paiementService.payer(payeur).subscribe((data) => {
      window.open(data.data, "_blank");
      localStorage.clear();

      this.service.updateImportation(this.suivieFormulaire.value.numero).subscribe(
        (data) => {
          if (data.status === "OK") {
            this.closeDialog();
          }
        }, (error) => {
        }
      )
    });


  }

  openDialogPrevisualiser() {
    localStorage.clear();
    this.service.downloadfichierPdf(this.suivieFormulaire.value.numero).subscribe((response) => {
      console.log(response);
      const file = new Blob([response], {type: 'application/pdf'});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    });
  }

  openDialogTelecharger() {
    localStorage.clear();
    this.service.downloadfichierPdf(this.suivieFormulaire.value.numero).subscribe((response) => {
      const file = new Blob([response], {type: 'application/pdf'});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    });
  }

}
