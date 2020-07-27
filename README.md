# Smart Buy

An e-commerce application built using Spring boot and Angular framework.

## Tech used

 - Spring boot
 - MySQL
 - JWT
 - Angular v8.0
 - Bootstrap v4.0
 - Amazon Web Services
 - Amazon corretto openJDK 8
 - Apache Tomcat

## SmartBuy API

 - It has a spring boot applciation servinf REST APIs that are wuthenticated by JWT.

### Deployment

```sh
git clone https://github.com/asifjalaludeen/smart-buy.git
cd smartbuy
mvn clean install
```
- Generated war file can be deployed in any app server like `Apache Tomcat` or can be started as a spring boot application

## SmartBuy UI

- It has the Angular framework code used for the client side.

### Deployment
```sh
git clone https://github.com/asifjalaludeen/smart-buy.git
cd smartbuy-ui
npm install
ng serve
```

## AWS Architecture

| AWS Resource | Component |
| ------ | ------ |
| EC2 | Spring boot app runs in Apache Tomcat |
| S3 | Angular app hosted as static web app |
| Cloudfront | S3 bucket is added to a cloudfront distribution |
| App Load balancer | API endpoint in EC2 is added tl an app LB |
| RDS | MySQL database is hosted |


### Endpoint

 - Use this URL to access the application [Smart Buy](https://d1lh8p4z08whem.cloudfront.net)
 - Prefer firefox browser as we face an SSL cert issue.

