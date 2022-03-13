CREATE TABLE user_reba_jwt (
    user_id NUMBER(10) PRIMARY KEY,
    dtype CHAR(1),
    user_name VARCHAR2(60) UNIQUE,
    user_password VARCHAR2(60),
    user_role VARCHAR2(20),
    user_mobile VARCHAR2(10),
    user_email VARCHAR2(20),
    user_city VARCHAR2(20)
);

CREATE TABLE broker_inherit_jwt (
    user_id NUMBER(10) PRIMARY KEY
);

CREATE TABLE customer_inherit_jwt (
    user_id NUMBER(10) PRIMARY KEY
);

CREATE TABLE property_inherit_jwt(
    prop_id NUMBER(10) PRIMARY KEY,
    brok_id_fk NUMBER(10) REFERENCES broker_inherit_jwt(user_id),
    cust_id_fk NUMBER(10) REFERENCES customer_inherit_jwt(user_id),
    prop_config VARCHAR2(20),
    offer_type VARCHAR2(20),
    offer_cost NUMBER(10,2),
    area_sqft NUMBER(10,2),
    address VARCHAR2(100),
    street VARCHAR2(20),
    city VARCHAR2(20),
    status NUMBER(1)
);

CREATE TABLE deal_inherit_jwt (
    deal_id NUMBER(10) PRIMARY KEY,
    cust_id_fk NUMBER(10) REFERENCES customer_inherit_jwt(user_id),
    prop_id_fk NUMBER(10) REFERENCES property_inherit_jwt(prop_id),
    deal_date DATE,
    deal_cost NUMBER(10,2)
);

SELECT * FROM user_reba_jwt;
SELECT * FROM broker_inherit_jwt;
SELECT * FROM customer_inherit_jwt;
SELECT * FROM property_inherit_jwt;
SELECT * FROM deal_inherit_jwt;