import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {TokenService} from "../service/token.service";
import {ServiceService} from "../service/service.service";
import {Importation} from "../models/Importation";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";
import Swal from 'sweetalert2'


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {
  displayedColumns = ['Numéro DII', 'Code PPM', 'Marchandise', 'Statut', 'Date Création'];
  dataSource!: MatTableDataSource<Importation>;

  soumise: number = 0;
  impayer: number = 0;
  brouillon: number = 0;
  terminer: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private  tokenService: TokenService, private service: ServiceService, private router: Router) {
    let id = this.tokenService.getUser().id;
    this.service.getImportationByUser(id).subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data.data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, (error) => {

      });
    this.service.getStatistiqueByUser(id).subscribe(
      (data) => {
        this.soumise = data.data.Soumise;
        this.brouillon = data.data.Brouillon;
        this.impayer = data.data.Impayer;
        this.terminer = data.data.Terminer;
      }, (error) => {

      });
  }

  ngOnInit(): void {


  }

  ngAfterViewInit() {

  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  logout() {
    Swal.fire({
      icon: "warning",
      title: 'Voulez vous vous déconnecter?',
      showDenyButton: true,
      confirmButtonText: `Oui`,
      confirmButtonColor: 'GREEN',
      denyButtonText: `NON`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        localStorage.clear();
        sessionStorage.clear()
        this.router.navigate(['']);
      } else if (result.isDenied) {
      }
    })


  }

}
