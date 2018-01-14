
export class User {
    id: string;
    name: string;
    email: string;
}

export class AuthUser {
    name: string = '';
    email: string = '';
    password: string = '';
}

export class Student {
    id: string;
    name: string;
    email: string;
    phoneNumber: string;
    groups: Group[]
}

export class Group {
    id: string;
    groupName: string;
    students: Student[];
    teacher: User;
}