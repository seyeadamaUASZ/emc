import {Component, OnInit} from '@angular/core';
import {FormGroup, Validators, FormBuilder} from '@angular/forms';
import {MatDialog} from "@angular/material/dialog";
import {Router} from '@angular/router';
import {MarchandiseComponent} from "../marchandise/marchandise.component";
import {ServiceService} from "../service/service.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2'
import {Utilisateur} from "../models/Utisateur";
import { InfoPayeurComponent } from '../info-payeur/info-payeur.component';
import { Payeur } from '../models/Payeur';
import { PaiementService } from '../service/paiement.service';


@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})


export class DemandeComponent implements OnInit {

  isLinear = false;
  cmsForm!: FormGroup;
  ImportationForm!: FormGroup;

  displayedColumns: string[] = ['Id', 'Description', 'Origine', 'Action'];
  dataSource: any;
  marchandises: any[] = [];
  document_1!: File;
  document_2!: File;
  document_3!: File;
  document_4!: File;
  document_5!: File;
  suivieFormulaire!: FormGroup;



  constructor(
    private _formBuilder: FormBuilder,
    public dialog: MatDialog,
    private service: ServiceService,
    private paiementService: PaiementService,

    private router: Router) {
  }

  

  ngOnInit() {

  


    this.cmsForm = this._formBuilder.group({
      id: [''],
      email: [''],
      telephone: ['', Validators.required],
      numero_chassis: [''],
      
    });
  
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(MarchandiseComponent, {
      width: '500',
    });
    dialogRef.afterClosed().subscribe(
      result => {
        this.dataSource = new MatTableDataSource(this.service.marchandises)
      }
    )
  }

  

  
  // selectDocument5(event: any): void {
  //   this.document_5 = this.ImportationForm.value.document_5;

  // }


  onSubmit() {
   
    const formData: FormData = new FormData();
    // formData.append('importation', JSON.stringify(this.ImportationForm.value));
    if (this.document_1)
      formData.append('document_1', this.document_1);
    if (this.document_2)
      formData.append('document_2', this.document_2);
    if (this.document_3)
      formData.append('document_3', this.document_3);
    if (this.document_4)
      formData.append('document_4', this.document_4);
    if (this.document_5)
      formData.append('document_5', this.document_5);

    this.service.createDemande(formData).subscribe(
      (data) => {
        if (data.status === "OK") {
          this.service.getUserByMail(this.ImportationForm.value.emailImportateur).subscribe(
            (dataUser) => {
              if (dataUser.status === "OK") {
                Swal.fire({
                  title: 'Votre demande a été soumise avec succès.',
                  text: 'Un mail vous est envoyé à l’adresse indiquée. Pour pouvoir payer les frais et suivre votre dossier, veuillez utiliser ce numéro de dossier N˚ ' + data.data.numero + '\n \n \n \n',
                  icon: 'success',
                  confirmButtonColor: "GREEN",
                  confirmButtonText: 'OUI',
                });
                this.router.navigate(['']); // tells them they've been logged out (somehow)

              } else {
                Swal.fire({
                  icon: 'success',
                  title: 'Votre demande a été soumise avec succès.',
                  text: 'Un mail vous est envoyé à l’adresse indiquée. Pour pouvoir payer les frais et suivre votre dossier, veuillez utiliser ce numéro de dossier N˚ ' + data.data.numero + '\n \n \n \n',
                  confirmButtonText: 'OUI',
                  confirmButtonColor: "GREEN",
                }).then((result) => {
                  /* Read more about isConfirmed, isDenied below */
                  if (result.isConfirmed) {
                    Swal.fire({
                      title: 'Information',
                      text: 'Souhaiteriez-vous disposer d’un compte ?',
                      icon: 'info',
                      showCancelButton: true,
                      confirmButtonText: 'Oui',
                      confirmButtonColor: 'green',
                      cancelButtonColor: "red",
                      cancelButtonText: 'Non'
                    }).then((result) => {
                      if (result.value) {
                        localStorage.setItem('numero', data.data.numero);
                        this.router.navigate(['register']); // tells them they've been logged out (somehow)
                      } else if (result.dismiss === Swal.DismissReason.cancel) {
                        this.router.navigate(['']); // tells them they've been logged out (somehow)

                        /*const utilisateur: Utilisateur = new Utilisateur();
                        utilisateur.numeroDii = data.data.numero;
                        utilisateur.email = data.data.emailImportateur;
                        utilisateur.telephone = data.data.telephoneImportateur;
                        formData.append('utilisateur', JSON.stringify(utilisateur));
                        this.service.inscriptionTemporaire(formData).subscribe((data) => {
                          },
                          (error) => {

                          });*/
                      }
                    });
                  } else if (result.isDenied) {
                    Swal.fire('Changes are not saved', '', 'info')
                  }
                })


              }
            }, (error) => {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Une Erreur est survenue',
              });
            });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une Erreur est survenue',
          });
        }

      }, (err: any) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Une Erreur est survenue',
        });
      });
  }


  brouillon() {
    this.ImportationForm.value.marchandise = this.marchandises;
    const formData: FormData = new FormData();
    formData.append('importation', JSON.stringify(this.ImportationForm.value));
    if (this.document_1)
      formData.append('document_1', this.document_1);
    if (this.document_2)
      formData.append('document_2', this.document_2);
    if (this.document_3)
      formData.append('document_3', this.document_3);
    if (this.document_4)
      formData.append('document_4', this.document_4);
    if (this.document_5)
      formData.append('document_5', this.document_5);

    this.service.createDemande(formData).subscribe(
      (data) => {
        if (data.status === "OK") {
          localStorage.clear()
          /*Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Votre demande N˚ ' + data.data.numero + ' a été a ete sauvegardée avec succès',
            showConfirmButton: true,
          })*/

          Swal.fire({
            title: 'Votre demande N˚ ' + data.data.numero + ' a été sauvegardée avec succès',
            showDenyButton: false,
            showCancelButton: false,
            confirmButtonText: `OK`,
            confirmButtonColor: 'GREEN',
            denyButtonText: `Don't save`,
            icon: 'success'
          }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
              //Swal.fire('Saved!', '', 'success')
              this.router.navigate(['']); // tells them they've been logged out (somehow)
            } else if (result.isDenied) {
              Swal.fire('Changes are not saved', '', 'info')
            }
          })

          /*Swal.fire(
            'Information',
            'Votre demande a été soumise avec succès en mode brouillon.',
            'success'
          )*/

        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une Erreur est survenue',
          })
        }

      }, (err: any) => {
        console.log(err);
      })
  }

  closeDialog() {
    this.router.navigate(['']);
  }

 
  openDialogDelete(element: any) {
    this.dataSource = this.dataSource.data.filter((value: any, key: any) => {
      return value.description != element.description;
    });
  }

  openPaiement(){
    const dialogRef = this.dialog.open(InfoPayeurComponent, {
      width: '600',
      backdropClass:'backdrop-bg-orange',
      panelClass:'bg-blue'
    });
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



}
