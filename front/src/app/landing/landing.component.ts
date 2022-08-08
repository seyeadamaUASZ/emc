import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SuivieComponent } from "../suivie/suivie.component";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ServiceService } from "../service/service.service";
import Swal from 'sweetalert2'


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  suivieForm!: FormGroup;

  constructor(public dialog: MatDialog, private _formBuilder: FormBuilder,
    private service: ServiceService) {
  }

  ngOnInit(): void {
    this.suivieForm = this._formBuilder.group({
      numero: ['', Validators.required],
      email: ['', Validators.required],
    })
  }

  goToSuivi() {
    localStorage.clear();
    let numero = this.suivieForm.value.numero;
    let email = this.suivieForm.value.email;
    this.service.getImportationByNumeroAndMail(numero, email).subscribe(
      (data) => {
        alert(JSON.stringify(data));

        if (data.status === "OK") {
          localStorage.setItem('idFacture', data.data.idInvoice);
          localStorage.setItem('Montant', data.data.valeurFacture);
          localStorage.setItem('Prenom', data.data.emailImportateur);
          localStorage.setItem('Nom', data.data.emailImportateur);
          localStorage.setItem('Username', data.data.emailImportateur);
          if (data.data.status == 2) {
            this.service.demandeQrCode(numero).subscribe((response: any) => {
            });
          } else {
            this.service.demandePdf(numero).subscribe((response: any) => {
            });
          }
          let Statut;
          if (data.data.status == 0)
            Statut = "Brouillon";
          else if (data.data.status == 1)
            Statut = "A Payer";
          else
            Statut = "Terminer";

          const dialogRef = this.dialog.open(SuivieComponent, {
            width: '800px',
            data: {
              'numeroDii': data.data.numero,
              'numero': data.data.numero,
              'status': data.data.status,
              'statusText': Statut,
              'statusButton': data.data.status,
              'dateCreation': data.data.dateCreation,
            },
          });

          this.suivieForm = this._formBuilder.group({
            numero: [''],
            email: [''],
          })


        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Numero chassis ou de dossier incorrect',
          })
        }

      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Erreur',
        })
      }
    )
  }
}
