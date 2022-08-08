import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from "@angular/material/dialog";
import { Payeur } from '../models/Payeur';
import { PaiementService } from "../service/paiement.service";

@Component({
  selector: 'app-info-payeur',
  templateUrl: './info-payeur.component.html',
  styleUrls: ['./info-payeur.component.css']
})
export class InfoPayeurComponent implements OnInit {

  url: any;

  factureForm = this.formbuild.group({
    idFacture: 1,
    montantFacture: [''],
    nomPayeur: [''],
    prenomPayeur: [''],
    usernamePayeur: [localStorage.getItem('username')],
  });

  constructor(private formbuild: FormBuilder,
    public dialogRef: MatDialogRef<InfoPayeurComponent>,
    private paiementService: PaiementService,
    @Inject(MAT_DIALOG_DATA) public element: any,
  ) {
  }


  ngOnInit(): void {
    this.factureForm.setValue({
      idFacture: 1,
      montantFacture: 100,
      nomPayeur: 'Seye',
      prenomPayeur: localStorage.getItem('prenom'),
      usernamePayeur: localStorage.getItem('username')

    });
  }

  get f() {
    return this.factureForm.controls;
  }

  validerpaiement = false;


  closeDialog() {
    this.dialogRef.close({ typeProcess: this.element.typeProcess, status: this.validerpaiement });
  }
  onSubmit() {
    const payeur: Payeur = new Payeur();

    // let idFature = localStorage.getItem('idFacture') ?? 0;
    let idFature = 10000;

    let montant =this.factureForm.value.idFacture ;
    let username=this.factureForm.value.prenomPayeur ;
    let prenom = this.factureForm.value.prenomPayeur;
    let nom = this.factureForm.value.nomPayeur;

    localStorage.clear();

    payeur.idFacture = idFature as number;
    payeur.montantFacture = montant;
    payeur.usernamePayeur = username;
    payeur.nomPayeur = prenom;
    payeur.prenomPayeur = nom;

    this.paiementService.payer(payeur).subscribe((data) => {
      window.open(data.data, "_blank");
      localStorage.clear();

      // this.service.updateImportation(this.suivieFormulaire.value.numero).subscribe(
      //   (data) => {
      //     if (data.status === "OK") {
      //       this.closeDialog();
      //     }
      //   }, (error) => {
      //   }
      // )
    });

  }
}
