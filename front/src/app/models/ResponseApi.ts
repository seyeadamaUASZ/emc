export class ResponseApi {
    status!: string;
    data?: any;
    message?: string;
    errors?: string;
    metadata?: ApiPaginator;
  }


  export class ApiPaginator{
    size!: number;
    totalElements!: number;
    totalPages!: number;
    numero!: number;   
  }