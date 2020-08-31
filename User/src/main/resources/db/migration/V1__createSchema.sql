CREATE TABLE IF NOT EXISTS Users (
    User_Id INT PRIMARY KEY Auto_Increment,
    User_Name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Detailed_User (
    User_Id INT PRIMARY KEY Auto_Increment,
    First_Name VARCHAR(255),
    Last_Name VARCHAR(255)
);

