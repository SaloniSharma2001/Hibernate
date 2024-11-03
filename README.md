# Hibernate
Self Learning | Tutorial | Implementations | Notes

**What is ORM?**
ORM stands for Object Relational Mapping

**What is Hibernate Framework?**
- It is a Java Framework that simplifies the development of Java application to interact with the database.
- It simplifies the JDBC work and manual DAO not needed because it takes care of it.
- It is an ORM tool, Open source, lightweight. (Object --  table mapping)
- Entire concept of Hibernate is based on POJOs [Plain Old Java Object] (simple classes without restriction)
- Hibernate is a non-invasive framework, it won't force the programmers to extend/implement any class/interface.
- It was developed by Gavin King in 2001.

**Why do we need ORM?**
To know the advantage of ORM we need to know the traditional way to save data (using JDBC).
We used to have a DB with tables in case of relational database and java application to interact with the DB. In our Java application we used to have an object where we used to add our data and with the help of JDBC API/Driver we used to save our data to the table. But there we had to write query to save the data into table manually at Java code level. (Data Access Object files we used to create.)

**Where Hibernate Play Its Role?**

We just create object >> Give it to Hibernate >> Hibernate saves this to table without creating DAO. It does with the help of Entity classes where we already map the column name of the table with class variable names.

Create a maven project and add its dependency from its web page to the <dependencies> tag in pom.xml file of maven project. And we can add many dependencies in the same way.

We can change JDK version of project by right clicking on project >> Properties >> Java Build Path >> Add Lib >> JRE system lib  >> System lib.

Without Maven we can download jar files from web browser.. 
Right click >> Build Path >>  Configure Build Path >> Class Path >> Add External Jars >> Locate and select all the Jar files from the saved section of downlaod. >> It'll get reflected in Reference lib.

**Hibernate Configuration**
We shall use XML file for configuration however, we can do that through Java as well. According to std we shall name the file as hibernate.cfg.xml

In the file we have used Session factory. It is an interface, factory for providing sessions. It is a thread-safe object and one project should have only one Session Factory. Each session helps us to perform Save, Delete, Update, etc like operations. 
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

**Hibernate Program using Annotations**
We create entity class and we can map tables to entity in two ways:
-- Using XML
-- Using Annotations

**Annotations**
The @Entity and @Table annotations serve different purposes in JPA (Java Persistence API):

@Entity(name="Saloni"):

@Entity is used to mark a class as a JPA entity, meaning it maps to a database table.
The name attribute specifies the name of the entity, which JPA uses internally (for example, in JPQL queries).
When you use @Entity(name="Saloni"), the entity is referred to as "Saloni" in the persistence context, even if the table it maps to has a different name.

`@Entity(name = "SaloniEntity")
@Table(name = "Saloni")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
`

Now, when querying this entity, you refer to it as SaloniEntity in JPQL queries, even though the class is named Person and the database table is Saloni.

`import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Person> findByName(String name) {
        return entityManager.createQuery(
                "SELECT s FROM SaloniEntity s WHERE s.name = :name", Person.class)
                .setParameter("name", name)
                .getResultList();
    }
}`

**@Table** -- It is used to change the table details.
**@Id** -- use to mark column as id(primary key).
**@GeneratedValue** -- hibernate will automatically generate values for that using an internal sequence. Therefore, we won't have to do that manually.

`  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;`

**@Column** -- Can be used to specify column mappings. Ex: to change the column name in the associated table in teh DB.
**@Transient** -- This tells hibernate not to save this field.
**@Temporal** -- @Temporal over a date field tells hibernate the format in which the date needs to be saved.
Ex: `public enum TemporalType {

    /** Map as <code>java.sql.Date</code> */
    DATE, 

    /** Map as <code>java.sql.Time</code> */
    TIME, 

    /** Map as <code>java.sql.Timestamp</code> */
    TIMESTAMP
}`
`@Column(name = "assigned_on", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date assignedIdDate;`

@**Lob** -- @Lob tells hibernate that this is a larger object, not a simple object.

There many other important annotations, for instance: @OneToOne, @OneToMany, @ManytoOne, @JoinColumn etc.

To fetch data we need not to write SELECT query at our end at, instead we use primary key to fetch data using entity class from the table.
Session interface have two method to fetch data get() and post().
<h5>FETCH DATA --> To get object(data) using hibernate we have two methods:</h5>
**get():** get method of Hibernate Session returns null if object is not found in cache as well as on database.
            get() involves database hit if object doesn't exists in Session Cache and returns a fully initialized object which may involve several databaase call.
            Use if you are not sure that object exists in DB or not.

**load():** load() method throws ObjectNotFoundException if object is not found on cache as well as on database but never return null.
            load method can return proxy in place and only initialize the object or hit the database if any method other than getId() is called on persistent or entity object. This lazy initialization increases the performance.
            Use if you are sure that object exists.
