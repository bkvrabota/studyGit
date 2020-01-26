----Book-----
INSERT INTO Book VALUES(1, true, 'Novel', 'Azbuka', 'Tolstoy L.N.', 'Voyna i Mir',
                        'description book 1', 1000, 1, '2018-11-15');
INSERT INTO Book VALUES(2, true, 'Fairy tale', 'AST', 'Folk', 'Kolobok',
                        'description book 2', 300, 1, '2019-10-17');
INSERT INTO Book VALUES(3, true, 'Detective', 'Eksmo', 'Christie A.', 'Murder on the Orient Express',
                        'description book 3', 500, 1, '2018-12-20');
----Client----
INSERT INTO Client VALUES(1, 1001, '88005553535', 'Ivan', 'Petrov');
INSERT INTO Client VALUES(2, 1002, '88005553536', 'Boris', 'Ivanov');
INSERT INTO Client VALUES(3, 1003, '88005553537', 'Jim', 'Jones');
----Orders-----
INSERT INTO Orders VALUES(1, 'New', 1, '2019-7-15', null, 1000);
INSERT INTO Orders VALUES(2, 'New', 3, '2019-12-10', null, 300);
INSERT INTO Orders VALUES(3, 'New', 2, '2019-11-5', null, 500);
----BuyBooks-----
INSERT INTO book_orders VALUES(1, 3);
INSERT INTO book_orders VALUES(2, 2);
INSERT INTO book_orders VALUES(3, 1);
----Requests----
INSERT INTO Requests VALUES(1, 3, '2019-5-15', 2, 'New');
INSERT INTO Requests VALUES(2, 2, '2019-10-5', 1, 'New');
INSERT INTO Requests VALUES(3, 1, '2019-11-11', 3, 'New');

