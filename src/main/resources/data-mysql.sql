INSERT INTO type_materiel (id,nom,photo) VALUES
(1,'vidéoprojecteur','../images/ecran.png'),
(2,'ordinateur','picture.png'),
(3,'casque VR','pic.png'),
(4,'webcam','img.jpeg');

INSERT INTO marque (id,nom) VALUES
(1,'HP'),
(2,'LENOVO');

INSERT INTO modele (id,nom, documentation, caution,photo, marque_id,type_materiel_id) VALUES
(1,'Casque 1','enlever ses lunettes',1000,'coucou.jpeg',1,3),
(2,'Ordinateur 1','fragile',550,'clv.jpeg',2,2),
(3,'Ordinateur 2','pas fragile',1500,'coucou.jpeg',1,2),
(4,'Vidéoproj 1','enlever le cache avant utilisation',50,'',1,1),
(5,'Webcam 1','mettre une gomette par dessus',10,'',2,4),
(6,'Ordinateur 3','ne pas utiliser le pavé tactile',10,'',2,2);

insert into etat_materiel (id,etat,observations) values
(1,'bon','bon état');

insert into materiel (id,num_serie,modele_id,etat_materiel_id,disponible) values
(1, '1010',2,1,true),
(2,'1020',2,1,false),
(3,'1030',2,1,true),
(4,'2010',3,1,true);

insert into utilisateur (id, admin, adresse, email, login, nom, password, prenom, telephone, num_token) values
(1,true, '8t rue robert schuman','mat@mat.com','mattasco','jacques','$2a$10$7kAMwyFvx.V8KOrC2YGWzuP1MnfDizeZ4Ys.sj6bBcccDWhZX8DPa','matthieu','0767394538',1),
(2,false,'86 rue aux arenes','joe@smith.fr','joe','smith','root','joe','0636505878',1);

insert into emprunt (id,date_debut_pret,date_fin_pret,materiel_id,valide, emprunteur_id) values
(1,'2022-05-25','2022-05-27',2,false,1),
(2,'2022-06-30','2022-06-29',4,false,2);
