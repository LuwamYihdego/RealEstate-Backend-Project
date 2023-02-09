insert into role(id, role) values (1, 'Admin');

insert into address(id, city, state, street, zip_code)
values(1, 'city', 'state', 'street', 'zip-code');

insert into users(id, email, first_name, is_active, last_name, password,phone_number,address_id,role_id)
values(1, 'email', 'fn', true,'ln','Abcd@1234', '+098777', 1, 1);