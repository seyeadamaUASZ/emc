import {Component, OnInit} from '@angular/core';
import {ServiceService} from "../service/service.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  inscriptionForm!: FormGroup;
  numero?: string | null;

  constructor(private service: ServiceService, private _formBuilder: FormBuilder, private router: Router) {
  }

  ngOnInit(): void {

    this.inscriptionForm = this._formBuilder.group({
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      email: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
    })

    this.numero = localStorage.getItem('numero');
    this.service.getImportationByNumero(this.numero)
      .subscribe(data => {
        this.inscriptionForm = this._formBuilder.group({
          prenom: ['', Validators.required],
          nom: ['', Validators.required],
          email: [data.data.emailImportateur, Validators.required],
          login: ['', Validators.required],
          password: ['', Validators.required],
        })
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Une Erreur est survenue',
        })      })

  }


  onSubmit() {
    const formData: FormData = new FormData();
    formData.append('utilisateur', JSON.stringify(this.inscriptionForm.value));
    formData.append('numero', JSON.stringify(this.numero));
    this.service.inscription(formData).subscribe((data) => {
      if (data.status === "OK") {
        Swal.fire({
          icon: 'success',
          title: 'Info',
          text: 'Inscription Reussi',
          timer: 1500
        });
        this.router.navigate(['login']); // tells them they've been logged out (somehow)
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Une Erreur est survenue',
        })
      }
    }, (error) => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Une Erreur est survenue',
      })
    })
  }

  /*suivreDemande(numero: string) {
    this.service.getImportationByNumero(numero)
      .subscribe(data => {
        alert('SUCCESS' + JSON.stringify(data));
      }, error => {
        alert('ERROR' + JSON.stringify(error));
      })
  }*/

}
