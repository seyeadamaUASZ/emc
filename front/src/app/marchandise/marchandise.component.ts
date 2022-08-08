import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {ServiceService} from "../service/service.service";

@Component({
  selector: 'app-marchandise',
  templateUrl: './marchandise.component.html',
  styleUrls: ['./marchandise.component.css']
})
export class MarchandiseComponent implements OnInit {

  formGroup!: FormGroup;
  id=0

  constructor(private _formBuilder: FormBuilder, public dialog: MatDialog, private  service: ServiceService) {
  }

  ngOnInit(): void {
    this.formGroup = this._formBuilder.group({
      origine: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  addMarchandise() {
    this.id=this.id+1
    this.service.addMarchandise(this.id,this.formGroup.value.origine, this.formGroup.value.description);
    this.dialog.closeAll();
  }

  closeDialog() {
    this.dialog.closeAll();
  }
}
