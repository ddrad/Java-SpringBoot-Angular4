export class RegisterData{
  public email:string;
  public firstName:string;
  public lastName:string;
  public middleName:string;
  public password:string;
  public customerType: string;


  constructor(email: string, firstName: string, lastName: string, middleName: string, password: string, customerType: string) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.password = password;
    this.customerType = customerType;
  }
}
