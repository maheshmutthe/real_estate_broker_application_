CREATE TABLE user_reba_inherit (
    user_id NUMBER(10) PRIMARY KEY,
    dtype CHAR(1),
    user_password VARCHAR2(20),
    user_role VARCHAR2(20),
    user_mobile VARCHAR2(10),
    user_email VARCHAR2(15),
    user_city VARCHAR2(15)
);

CREATE TABLE broker_inherit (
    user_id NUMBER(10) PRIMARY KEY,
    bro_name VARCHAR2(20)
);

CREATE TABLE customer_inherit (
    user_id NUMBER(10) PRIMARY KEY,
    cust_name VARCHAR2(20)
);

CREATE TABLE property_inherit(
    prop_id NUMBER(10) PRIMARY KEY,
    brok_id_fk NUMBER(10) REFERENCES broker_inherit(user_id),
    cust_id_fk NUMBER(10) REFERENCES customer_inherit(user_id),
    prop_config VARCHAR2(20),
    offer_type VARCHAR2(20),
    offer_cost NUMBER(10,2),
    area_sqft NUMBER(10,2),
    address VARCHAR2(100),
    street VARCHAR2(20),
    city VARCHAR2(20),
    status NUMBER(1)
);

CREATE TABLE deal_inherit (
    deal_id NUMBER(10) PRIMARY KEY,
    cust_id_fk NUMBER(10) REFERENCES customer_inherit(user_id),
    prop_id_fk NUMBER(10) REFERENCES property_inherit(prop_id),
    deal_date DATE,
    deal_cost NUMBER(10,2)
);

CREATE SEQUENCE user_reba_seq
    MINVALUE 100000
    MAXVALUE 900000
    START WITH 100000
    INCREMENT BY 1;
    
CREATE SEQUENCE prop_reba_seq
    MINVALUE 100000
    MAXVALUE 900000
    START WITH 100000
    INCREMENT BY 1;
    
CREATE SEQUENCE deal_reba_seq
    MINVALUE 100000
    MAXVALUE 900000
    START WITH 100000
    INCREMENT BY 1;