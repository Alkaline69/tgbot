DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

-- user schema
CREATE TABLE tSex (
    SexID INTEGER  PRIMARY KEY
   ,Name VARCHAR(17) NOT NULL
);

CREATE TABLE tCountry (
    CountryID INTEGER  PRIMARY KEY
   ,Name VARCHAR(50) NOT NULL);

CREATE TABLE tDiscount ( DiscountID INTEGER  PRIMARY KEY
						,ProductID INTEGER NOT NULL UNIQUE
						,Discount TINYINT CHECK (Discount BETWEEN 0 and 100));

CREATE TABLE tType ( TypeID INTEGER  PRIMARY KEY
				  , Name VARCHAR(50) NOT NULL);

CREATE TABLE tFamily ( FamilyID INTEGER  PRIMARY KEY
					  ,Name VARCHAR(20) NOT NULL);

CREATE TABLE tNote ( NoteID INTEGER  PRIMARY KEY
					,Name VARCHAR(50) NOT NULL);

CREATE TABLE tBrand ( BrandID INTEGER PRIMARY KEY
					 ,Name VARCHAR(50) NOT NULL);

CREATE TABLE tProduct ( ProductID INTEGER  PRIMARY KEY
					   ,Name VARCHAR(50) NOT NULL
					   ,Cost NUMERIC(10,2)
					   ,Vol TINYINT
					   ,RealizeDate date
					   ,typeID INTEGER
					   ,CountryID INTEGER
					   ,SexID INTEGER
					   ,BrandID INTEGER
					   ,FamilyID INTEGER
					   ,NoteID INTEGER);
