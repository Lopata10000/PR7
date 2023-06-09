CREATE TABLE Plays (
                       ID SERIAL PRIMARY KEY,
                       Title VARCHAR(100),
                       Author VARCHAR(100),
                       Year_Written INT
);

CREATE TABLE Actors (
                        ID SERIAL PRIMARY KEY,
                        FirstName VARCHAR(50),
                        LastName VARCHAR(50),
                        BirthDate DATE
);

CREATE TABLE Performances (
                              ID SERIAL PRIMARY KEY,
                              PlayID INT,
                              DateTime TIMESTAMP,
                              FOREIGN KEY (PlayID) REFERENCES Plays(ID)
);

CREATE TABLE Roles (
                       ActorID INT,
                       PerformanceID INT,
                       RoleName VARCHAR(50),
                       FOREIGN KEY (ActorID) REFERENCES Actors(ID),
                       FOREIGN KEY (PerformanceID) REFERENCES Performances(ID)
);
