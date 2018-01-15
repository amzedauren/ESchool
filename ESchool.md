# ESchool

	# this application intended to register pupils to courses
    # preview available on http://ec2-52-15-52-102.us-east-2.compute.amazonaws.com:8080
    
	# Stack: Java EE + MyBatis + PostgreSQL + Angular5 + Material

## Follow steps to run application. 

### Step 1: source code
	
    # clone repository 
    $ git clone https://github.com/amzedauren/ESchool.git
    

### Step 2: database 

	# you need to run ./sql/script.sql script on your postgreSQL database 
    # and specify your database credentials and url into ./ESchool/backend/src/main/webapp/WEB_INF/mybatis-config.xml file
    
### Step 3: backend
	
    # go to /backend folder
    $ cd backend
    # you can package app with following command
    $ mvn package
    # then deploy it to Wildfly server, or you can simply run on localhost using
    $ mvn wildfly:run
    # command, then backend will be available on http://localhost:8080
    # all needed dependencies and server maven installs itself
   
### Step 4: Frontend

	# if you run backend on different endpoint, you have to specify endpoint into 
    # ./Eschool/frontend/src/app/config.ts config file
    
    # go to /frontend folder
    $ cd frontend
    
    # get all dependencies
    $ npm install
    
    # package 
    $ npm run-script build
    
    # as result we have generated files in /dist folder, you can deploy it somewhere or just run index.html file on browser.
    
    # or you can run on debug mode with command
    $ npm start
    # it will be available on http://localhost:4200
    
    
    


	
    


    
   
	
