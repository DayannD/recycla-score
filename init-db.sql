-- Création de la table Produits
CREATE TABLE IF NOT EXISTS Produits (
                                        ID_Produit INT AUTO_INCREMENT PRIMARY KEY,
                                        Nom_Produit VARCHAR(255) NOT NULL,
    Description TEXT,
    Score_Recyclabilite FLOAT,
    Poids FLOAT,
    Volume FLOAT,
    URL_Image VARCHAR(255)
    );

-- Création de la table Matériaux
CREATE TABLE IF NOT EXISTS Materiaux (
                                         ID_Materiau INT AUTO_INCREMENT PRIMARY KEY,
                                         Nom_Materiau VARCHAR(255) NOT NULL,
    Type_Recyclage VARCHAR(100),
    Cout_Recyclage FLOAT,
    Energie_Recyclage FLOAT
    );

-- Création de la table ProduitMatériaux
CREATE TABLE IF NOT EXISTS Produit_Materiaux (
                                                ID INT AUTO_INCREMENT PRIMARY KEY,
                                                ID_Produit INT,
                                                ID_Materiau INT,
                                                Quantite FLOAT,
                                                FOREIGN KEY (ID_Produit) REFERENCES Produits(ID_Produit),
    FOREIGN KEY (ID_Materiau) REFERENCES Materiaux(ID_Materiau)
    );

-- Création de la table Utilisateurs
CREATE TABLE IF NOT EXISTS Utilisateurs (
                                            ID_Utilisateur INT AUTO_INCREMENT PRIMARY KEY,
                                            Nom_Utilisateur VARCHAR(255) NOT NULL,
    Mot_de_Passe VARCHAR(255) NOT NULL,
    );

CREATE TABLE IF NOT EXISTS Tags (
                                    ID_Tag INT AUTO_INCREMENT PRIMARY KEY,
                                    Nom_Tag VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Produit_Tags (
                                            ID INT AUTO_INCREMENT PRIMARY KEY,
                                            ID_Produit INT,
                                            ID_Tag INT,
                                            FOREIGN KEY (ID_Produit) REFERENCES Produits(ID_Produit),
    FOREIGN KEY (ID_Tag) REFERENCES Tags(ID_Tag)
    );

INSERT INTO Produits (Nom_Produit, Description, Score_Recyclabilite, Poids, Volume) VALUES ('Produit A', 'Description du Produit A', 0.8, 1.2, 0.003),
('Produit B', 'Description du Produit B', 0.6, 0.8, 0.002),
('Produit C', 'Description du Produit C', 0.7, 1.5, 0.004),
('Produit D', 'Description du Produit D', 0.9, 0.9, 0.001);

INSERT INTO Materiaux (Nom_Materiau, Type_Recyclage, Cout_Recyclage, Energie_Recyclage) VALUES ('Plastique', 'Thermoplastique', 0.05, 0.1),
('Aluminium', 'Métal', 0.15, 0.2),
('Verre', 'Verre', 0.1, 0.15),
('Papier', 'Papier', 0.02, 0.05);

INSERT INTO Produit_Materiaux (ID_Produit, ID_Materiau, Quantite) VALUES (1, 1, 0.5),
(1, 2, 0.5),
(2, 3, 1.0),
(3, 4, 0.3),
(4, 1, 0.2),
(4, 2, 0.8);

INSERT INTO Utilisateurs (Nom_Utilisateur, Mot_de_Passe, Role) VALUES ('admin', 'adminpass'),
('user', 'userpass');

INSERT INTO Tags (Nom_Tag) VALUES ('Ecology'),
                                  ('Paper & Cardboard'),
                                  ('Organic'),
                                  ('Batteries & D3E'),
                                  ('Plastic'),
                                  ('Glass'),
                                  ('VHU'),
                                  ('Wood'),
                                  ('Metal'),
                                  ('Hazardous Waste');

INSERT INTO Produit_Tags (ID_Produit, ID_Tag) VALUES (1, 1),
                                                     (2, 2),
                                                     (3, 3),
                                                     (4, 4);