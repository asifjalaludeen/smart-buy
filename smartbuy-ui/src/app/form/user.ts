export class User {
	username: string;
	emailId: string;
	password: string;
	phoneNumber: string;
	role: string;
	name: string;

	constructor(username?: string, emailId?: string, password?: string, phoneNumber?: string, 
		role?: string, name?: string) {
		this.username = username;
		this.emailId = emailId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.name = name;
	}
}