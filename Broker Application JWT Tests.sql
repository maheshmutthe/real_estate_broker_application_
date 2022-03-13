SELECT * FROM user_reba_jwt;
SELECT * FROM broker_inherit_jwt;
SELECT * FROM customer_inherit_jwt;
SELECT * FROM property_inherit_jwt;
SELECT * FROM deal_inherit_jwt;

DELETE FROM customer_inherit_jwt WHERE user_id = 100221;
DELETE FROM user_reba_jwt WHERE user_id = 100221;
 
commit;

DROP TABLE user_reba_jwt;
DROP TABLE broker_inherit_jwt;
DROP TABLE customer_inherit_jwt;
DROP TABLE property_inherit_jwt;
DROP TABLE deal_inherit_jwt;

INSERT INTO property_inherit_jwt VALUES (100,100249,null,'House','Buy',2500.0,250.0,'Mangoor','Bhat','Vasco',1);
INSERT INTO property_inherit_jwt VALUES (101,100249,null,'Villa','Buy',2500.0,250.0,'Bogmalo','Chicolna','Vasco',1);

UPDATE property_inherit_jwt SET status = 1 WHERE prop_id = 101;