# Electricity_Billing_System

# Description:
    An electricity billing system is a system that calculates and generates bills for the consumption of electricity by customers.
    It typically includes a meter to measure the amount of electricity used, a database to store customer information and usage data,
    and a billing module to calculate the charges based on the rates established by the utility company.
    The system may also provide the ability to view and pay bills online, as well as track usage and consumption history.

![Project Overview](https://user-images.githubusercontent.com/49556058/213899672-7d83a298-28b1-4706-80ea-9625eb00f888.png)

# Introduction: 
![Intoduction](https://user-images.githubusercontent.com/49556058/213899508-ec46ddc5-50f5-406d-83bc-4bc2fdebec10.png)


# Requirements: 
![Requirement](https://user-images.githubusercontent.com/49556058/213899745-3a82665d-e6e0-492e-a90e-40b1b0f040a7.png)



# Project Steup: 
![Project Setup](https://user-images.githubusercontent.com/49556058/213899784-514c4ea9-52f9-4182-9744-26be3d08e62c.png)

# For Electricity Billing System Project Database:

    0. (mysql-connector-java-8.0.28.jar and rs2xml.jar) these libraries are used in this project:
        For Databases connection
        For Tables creation:

    Execute theses Queries one by one in our MySql database:

    1. Create Database

        create database ebs;

    2. Use the Database you have just Created:

        use ebs;

    3. Create login Table to Store the information of the user:

        create table login(meter_no varchar(20), username varchar(30), name varchar(30), password varchar(30), user varchar
        (30));

    4. Create customer Table to store the information of customer:

        create table customer(name varchar(30), meter varchar(20), address varchar(50), city varchar(20), state varchar(30), email varchar(30), phone varchar(20));

    5. Create meter_info table to store the meter information of the customer:

        create table meter_info(meter_number varchar(20), meter_location varchar(20), meter_type varchar(20), phase_code varchar(20), bill_type varchar(20), days varchar(20));

    6. Create tax Table to store the current tax structure:

        create table tax(cost_per_unit varchar(20), meter_rent varchar(20), service_charge varchar(20), service_tax varchar(20), swachh_bharat_cess varchar(20), fixed_tax varchar(20));

    7. Insert some values into tax table:

        insert into tax_table values('9', '47', '22', '57', '6', '18');

    8. Create bill table to store the billing information of the customer:

        create table bill(meter varchar(20), month varchar(20), units varchar(20), total_bill varchar(20), status varchar(20));
    
    
# Components:
![image](https://user-images.githubusercontent.com/49556058/213899819-53ccdd06-5502-44dd-86fa-d7c3806e0cd1.png)


# Application Flow Diagram:

    An electricity billing system in Java flow diagram would typically involve the following steps:

        Data input: The customer's meter reading, name, address, and account number are entered into the system through a
    user interface built using Java.

        Bill calculation: The system calculates the bill amount using Java programming logic based on the meter reading, customer information, and the rates established by the utility company.

        Bill generation: The system generates the bill in the form of a pdf or other document using Java libraries such as iText.

        Bill distribution: The bill is distributed to the customer via email or an online portal built using Java technologies such as Servlets and JSP.

        Payment processing: The customer can view and pay the bill using a Java-based web application that integrates with payment gateways.

        Payment confirmation: The system records the payment and updates the customer's account in the database using Java database connectivity (JDBC)



# How To Run Java Desktop Application: 

  # First Run About.java
        ![About](https://user-images.githubusercontent.com/49556058/213900108-d12082b9-51fb-4cc9-9772-484ac269c9ca.png)

  # After Click on Button Get Started, Open Splash Frame
        ![Splash](https://user-images.githubusercontent.com/49556058/213900112-3deecf5a-47c0-4dd7-8ddf-391a7b994d5a.png)

  # After 5 sec, automatically open login frame.
        ![Login](https://user-images.githubusercontent.com/49556058/213900121-a3c52fda-4dff-4ae8-bec6-a32c13c684cd.png)

  4. On this frame either you login with admin or customer aur you can signup frame or cancel.
    1. Once successfully login with admin:
      1. You have shown 3 menu i.e Master, Utility, Logout
        ![image](https://user-images.githubusercontent.com/49556058/213900149-3f548026-fc52-42d2-a4f9-917df2379275.png)
        1. Inside Master menu:
          1. New Customer: For adding new customer
            ![new customer](https://user-images.githubusercontent.com/49556058/213900173-8c5ab6aa-6a75-48c7-87a7-ab1bb44bd3b8.png)
          2. Customer Details: For see the customer details
            ![customer details](https://user-images.githubusercontent.com/49556058/213900186-0a8b54dc-972c-477f-b9af-426eeb3f359a.png)
          3. Deposit Details: For see the depeosit details of customer
            ![deposit details](https://user-images.githubusercontent.com/49556058/213900199-9b7d1437-b7a3-4d7c-aa17-ac3e07cd965f.png)
          4. Calculate Bill: For the calculate the bill of customer which consume by them.
            ![calculate bill](https://user-images.githubusercontent.com/49556058/213900215-298485c0-e0d1-4cd8-a840-862f3ed08400.png)
        2. Inside Utility menu:
          1. Notepad: For uses of notepad simply open by click
            ![notepad](https://user-images.githubusercontent.com/49556058/213900239-f789471f-c865-4ad5-be1c-d039e382c408.png)
          2. Calculator: For uses of calculator simply open by click
            ![calculator](https://user-images.githubusercontent.com/49556058/213900247-f39483a2-9e97-4494-ae46-6399fd86f353.png)
          3. Web Browser: For uses of Web Browser simply click on it.
        3. Inisde Logout: simply logout the from admin, again open the login frame
      
   2. Once successfully login with customer:
      1. You have shown 5 menu with Information, User, Report, Utility, Logout
      ![customer](https://user-images.githubusercontent.com/49556058/213900430-922b755a-0be3-4237-9278-ee58a42c442d.png)
        1. Inside Information Menu:
          1. Update Information: If you want update the information of the user:
            ![update customer information](https://user-images.githubusercontent.com/49556058/213900581-d525a445-9843-4dbc-8a5a-62479eb79a25.png)
          2. View Information: If you want see the information of the user:
            ![view customer information](https://user-images.githubusercontent.com/49556058/213900612-4cb89c1c-2408-4409-8709-2c85922e4b26.png)
        2. Inside User Menu:
          1. Pay Bill: For Pay the Bill of the user
            ![pay bill](https://user-images.githubusercontent.com/49556058/213900659-6fe62c43-61ff-436d-93f0-395625c82f5b.png)
          2. Bill Details: For See the Bill Details of the user
            ![bill details](https://user-images.githubusercontent.com/49556058/213900683-e9585a48-59aa-496c-ac84-d01c748075dd.png)
        3. Inside Report Menu:
          1. Generate Bill: For Generate the Bill for the specific Month
            ![generate bill](https://user-images.githubusercontent.com/49556058/213900733-21a4cb6d-8fe4-42b7-a8ea-9ae3f153880d.png)
          2. Last Bill: For See the all previous Bill of the User
            ![last bill](https://user-images.githubusercontent.com/49556058/213900815-4cfcf6e5-c16a-46cb-b09b-307fee863054.png)
        4. Inside Utility menu:
          1. Notepad: For uses of notepad simply open by click
            ![notepad](https://user-images.githubusercontent.com/49556058/213900239-f789471f-c865-4ad5-be1c-d039e382c408.png)
          2. Calculator: For uses of calculator simply open by click
            ![calculator](https://user-images.githubusercontent.com/49556058/213900247-f39483a2-9e97-4494-ae46-6399fd86f353.png)
          3. Web Browser: For uses of Web Browser simply click on it.
        5. Inisde Logout: simply logout the from admin, again open the login frame
    3. If click on Cancel Buttton: 
      Then Simply close the application.

