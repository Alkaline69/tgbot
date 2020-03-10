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

CREATE TABLE tProductNotes (
     product_id  INTEGER NOT NULL
	,note_id     INTEGER NOT NULL

	,constraint pk_tProductNotes             primary key (product_id, note_id)
    ,constraint FK_tProductNotes_PRODUCT_ID  foreign key (product_id)    references tProduct (ProductID)
    ,constraint FK_tProductNotes_NOTE_ID     foreign key (note_id)       references tNote (NoteID)
);