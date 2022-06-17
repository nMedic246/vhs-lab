INSERT INTO "user" (username,name,surname,password,role) VALUES
                    ('nika123','nika','medic','$2a$10$XP4EX.rK83rOa/Q376D2yeTCxR8reDuhrRjVX8tmfbX/kgWfG8GyC','ROLE_ADMIN'),
                       ('jonny123','John','Doe','$2a$10$NcUfqC/k5bL/NBFb.lRvcev5fHNFcF/jkfIojyaMPz0AP8He5GIQO','ROLE_USER'),
                       ('yozo','Jozo','Jurišić','$2a$10$orLpR/2EeyrUVh74n..RSeiSrs9QWS3lyHykmEjCGBUiWTiO3mHMS','ROLE_USER');

INSERT INTO VHS (name,duration,year_published) VALUES
    ('Back to the future','01:56:00',1985),
    ('Se7en','02:07:00',1995),
    ('Mean girls','01:37:00',2004);

INSERT INTO rental (idVHS,rental_date,username,fee,is_returned) VALUES (1,'2017-04-18 00:00:00','nika123',20,false);
