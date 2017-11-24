export class TokenData {
  public token: string;
  public status: string;
  public customerType: string;
  public message: string;


  constructor(token: string, status: string, customerType: string, message: string) {
    this.token = token;
    this.status = status;
    this.customerType = customerType;
    this.message = message;
  }

}
