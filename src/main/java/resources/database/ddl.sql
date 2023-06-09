CREATE TABLE Plays (
                       ID SERIAL PRIMARY KEY,
                       Title VARCHAR(100),
                       Author VARCHAR(100),
                       Year_Written INT
);

CREATE TABLE Actors (
                        ID SERIAL PRIMARY KEY,
                        First_Name VARCHAR(50),
                        Last_Name VARCHAR(50),
                        Birth_Date DATE
);

CREATE TABLE Performances (
                              ID SERIAL PRIMARY KEY,
                              Play_ID INT,
                              Date_Time TIMESTAMP,
                              FOREIGN KEY (Play_ID) REFERENCES Plays(ID)
);

CREATE TABLE Roles (
                       Actor_ID INT,
                       Performance_ID INT,
                       Role_Name VARCHAR(50),
                       FOREIGN KEY (Actor_ID) REFERENCES Actors(ID),
                       FOREIGN KEY (Performance_ID) REFERENCES Performances(ID)
);
