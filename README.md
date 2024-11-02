# Hibernate
Self Learning | Tutorial | Implementations | Notes

**What is ORM?**
ORM stands for Ibject Relational Mapping

**What is Hibernate Framework?**
- It is a Java Framework that simplifies the development of Java application to interact with the database.
- It simplifies the JDBC work and manual DAO not needed because it takes care of it.
- It is an ORM tool, Open source, lightweight. (Object --  table mapping)
- Entire concept of Hibernate is based on POJOs Plain Old Java Object (simple classes without restriction)
- Hibernate is a non-invasive framework, it won't force the programmers to extend/implement any class/interface.
- It was developed by Gavin King in 2001

**Why do we need ORM?**
To know the advantage of ORM we need to know the traditional way to save daata (JDBC)
We used to have a DB with tables in case of relational database and java application to interact with the DB. In our Java application we used to have an object where we used to add our data and with the help of JDBC API/Driver we used to save our data to the table. But there we had to write query to save the data into table manually at Java code level. (Data Access Object files we used to create.)

**Where Hibernate Play Its Role?**

We just create object >> Give it to Hibernate >> Hibernate saves this to table without creating DAO. It does with the help of Entity classes where we already map the column name of the table with class variable names.

Create a maven project and add its dependency from its web page to the <dependencies> tag in pom.xml file of maven project. And we can add many dependencies in the same way.

We can change JDK version of project by right clicking on project >> Properties >> Java Build Path >> Add Lib >> JRE system lib  >> System lib.

Without Maven we can download jar files from web browser.. 
Right click >> Build Path >>  Configure Build Path >> Class Path >> Add External Jars >> Locate and select all the Jar files from the saved section of downlaod. >> It'll get reflected in Ref lib.

**Hibernate Configuration**
We shall use XML file for configuration however we can do that through Java as well. According to std we shall name the file as hibernate.cfg.xml

In the file we have used Session factory. It is an interface, factory for providing sessions. It is a thread-safe object and one project should have only Session Factory. Each session helps us to perform Save, Delete, Update, etc like operations. 
In Hibernate, a SessionFactory is a factory class that creates Session objects and allows users to perform database operations:
**What it does**
The SessionFactory is a key component of the JBoss persistence framework class library. It's responsible for creating Session objects, which are used to perform database operations like save, delete, and update. The SessionFactory also handles database connectivity, connection pooling, thread pooling, and JNDI interactions.
**How it's used**
Most applications create a single SessionFactory object, which is cached for the duration of the application's lifecycle. This is because creating a SessionFactory object is resource-intensive.
**How to get a session**
The SessionFactory has three methods for getting Session objects:
getCurrentSession(): Returns the session bound to the context.
openSession(): Opens a new session, which should be closed after all database operations are complete.
**Related concepts**
The SessionFactory's JPA companion class is the EntityManagerFactory. Modern applications are recommended to interact with the JPAs as much as possible.
