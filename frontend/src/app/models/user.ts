export class User {
  id: number;
  userName: string;
  email: string;
  password: string;
  role: string;
  errors: Map<string, string>;
}
