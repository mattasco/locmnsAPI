INSERT INTO type_materiel (id,nom) VALUES
(1,'souris'),
(2,'clavier'),
(3,'ecran PC');

INSERT INTO marque (id,nom) VALUES
(1,'DELL'),
(2,'LENOVO');

INSERT INTO modele (id,nom, marque_id,type_materiel_id) VALUES
(1,'PC01',1,3),
(2,'LASER02',1,1);

INSERT INTO materiel (id, photo, doc, prix_achat, modele_id) VALUES
(1, 'photo1','a conserver au frais',1500,1),
(2,'photo2','fragile',20,2);

