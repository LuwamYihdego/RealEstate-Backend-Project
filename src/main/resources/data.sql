insert into role(id, role, created_date) values(1, 'Admin', TO_DATE('19/02/2023', 'DD/MM/YYYY'));
insert into role(id, role, created_date) values(2, 'Owner', TO_DATE('19/02/2023', 'DD/MM/YYYY'));
insert into role(id, role, created_date) values(3, 'Customer', TO_DATE('19/02/2023', 'DD/MM/YYYY'));

insert into address(id, street, city, state, zip_code) values(100000, '1000N 4th St', 'Fairfield', 'IA', '52557');

insert into users(id, email, first_name, last_name, phone_number, is_active, password, address_id, role_id) values(100000, '3g.mit02@gmail.com', 'Gebreegziabher', 'Gebru', '+12246399556', true, '$2y$10$K5qgybrE53LEpwNfOl4ROOBTxr4wXi9hfPGDn.ApO6RU.f0psRghy',100000, 1);