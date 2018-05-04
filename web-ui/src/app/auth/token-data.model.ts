export class TokenData {
  public token: string;
  public status: string;
  public customerType: string;
  public message: string;
  public data;


  constructor(token: string, status: string, customerType: string, data, message: string) {
    this.token = token;
    this.status = status;
    this.customerType = customerType;
    this.data = data;
    this.message = message;
  }

}
