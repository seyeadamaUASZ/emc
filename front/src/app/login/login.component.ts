import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ServiceService} from '../service/service.service';
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {TokenService} from "../service/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private _formBuilder: FormBuilder,
    private service: ServiceService,
    private tokenService: TokenService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.loginForm = this._formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    this.service.authentification(this.loginForm.value).subscribe(
      (data) => {
        if (data.status === "OK") {
          this.router.navigate(['dashboard']);
          this.tokenService.saveToken(data.data.accessToken);
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Email ou Password Incorrect',
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
}
