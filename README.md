# Hibernate
Self Learning | Tutorial | Implementations | Notes

**What is ORM?**
ORM stands for Object Relational Mapping

**What is Hibernate Framework?**
- It is a Java Framework that simplifies the development of Java application to interact with the database.
- It simplifies the JDBC work and manual DAO not needed because it takes care of it.
- It is an ORM tool, Open source, lightweight. (Object --  table mapping)
- Used in teh data layer of application.
- Implements JPA (Java Persistence API) A set of standards that have been prescribed for any persistent implementation that needs to be met in order to get certified as a Java persistence API implementation so, it means is thar it follows the rules that have been set in the Java persistence API specification. Later if we don't want to use the hibernate and switch to different framework that follows the same JPA API we can do that with minimal changes.
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

**@Entity(name="Saloni"):**

@Entity is used to mark a class as a JPA entity, meaning it maps to a database table.
The name attribute specifies the name of the entity, which JPA uses internally (for example, in JPQL queries).
When you use @Entity(name="Saloni"), the entity is referred to as "Saloni" in the persistence context, even if the table it maps to has a different name.
In short, If we have marked a name attribute to an Entity class, it will create the table with name specified in the attribute part instead of taking on the name of the class itself.

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


**@Basic** -- A basic type maps direcly to a column in the database. These includes Java primitives and their wrapper classes. The @Basic annotation on a field or a property signifies that it’s a basic type and Hibernate should use the standard mapping for its persistence.
-  it’s an optional annotation.
-  we don’t specify the @Basic annotation for a basic type attribute, it is implicitly assumed, and the default values of this annotation apply.
-  @Basic annotation has two attributes, optional and fetch.
-  The optional attribute is a boolean parameter that defines whether the marked field or property allows null. It defaults to true. So, if the field is not a primitive type, the underlying column is assumed to be nullable by default.
-  The fetch attribute accepts a member of the enumeration Fetch, which specifies whether the marked field or property should be lazily loaded or eagerly fetched. It defaults to FetchType.EAGER, but we can permit lazy loading by setting it to FetchType.LAZY.
(Lazy loading will only make sense when we have a large Serializable object mapped as a basic type, as in that case, the field access cost can be significant.)
` 
//If we want not null and  and lazy load as well.
@Basic(optional = false, fetch = FetchType.LAZY)
    private String name;`

- We should explicitly use the @Basic annotation when willing to deviate from the default values of optional and fetch.

**_JPA @Basic vs @Column_**

1) Attributes of the @Basic annotation are applied to JPA entities, whereas the attributes of @Column are applied to the database columns.
2) @Basic annotation’s optional attribute defines whether the entity field can be null or not; on the other hand, @Column annotation’s nullable attribute specifies whether the corresponding database column can be null.
3) We can use @Basic to indicate that a field should be lazily loaded.
4) The @Column annotation allows us to specify the name of the mapped database column.

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


<p align="center"> **@Embeddable Annotation** </p>
These annotations are used in combination to allow the properties of one class to be included as a value type in another class and then be persisted in the database as part of the containing class.
**For instance:**

Entity 1

`Class Student{
int id;
String name
Private Certificate;
}`

Entity 2

`@Embeddable
Class Certificate{
String name;
String duration;
}
`

If we don't have table name for Entity class 2 and we want to embed it in 1 so that at the time of saving the data hibernate create the table mapping with name of the column of the 2nd class, we shall use _@Embeddable _annotation.


Mapping: Every Entity class can be mapped and the mapping can be uni-directional or bi-directional.

<p align="center"> **One To One Mapping** </p>
One to one represents that a single entity is associated with a single instance of the other entity. An instance of a source entity can be at most mapped to one instance of the target entity. 
In database management systems one-to-one mapping is of two types:-

<ul>One-to-one unidirectional</ul>
<ul>One-to-one bidirectional</ul>

![image](https://github.com/user-attachments/assets/8029adcc-ca37-446b-a1c7-cb1b4468055b) 

mappedBy property in @OneToOne mapping
In OneToOne mapping if we don't use mapped by and do bidirectional mapping, the result after the code execution will give us both table from question and answer entity having one column dedicated to foreign key. For question, it will be answer's primary key and for Answer table fk will be question's primary key. However if we use mapped by in any of these entity only that table will contain the mapping data and it will work same as bidirectional OneToOne mapping.

<p align="center"> One To Many Mapping </p>
One to many represents that one table can have multiple mapping to a different table. Here also we can do bidirectional mapping and upon mapping their will be third table dedicated to bidirectional mapping. To avoid the creation of third table we can use 

<p align="center">  Many To Many Mapping </p>
![image](https://github.com/user-attachments/assets/9d65df3a-cd9f-4ef4-a9e4-90838789bba6)

<p>

**Hibernate Fetching Technique**

**_Fetch Type_**

There are two type fetching technique in hibernate:-

<li>Eager</li> 
It is a design pattern in which data loading occurs on the spot.
Ex: If we are loading question from question table where answer's pk is also a mapped by any kind of mapping then everything including answer data will be loaded all at once.

<li>Lazy</li>
In Lazy loading, associated data loads only when we explicitly call getter or size method.
Ex: If we have mapped question and answer table then only question will be loaded if we are trying to get the question table data and mapped answer table will be loaded if and only if we call getter and setter or size method on any of the answer property.

![image](https://github.com/user-attachments/assets/fa513132-1193-44c6-9af1-29d99f2c48e2)

Bydefault if we haven't put any attribute value in mapping annotation related to fetch, for example: @OneToMany(mappedBy="question", fetch = FetchType.EAGER) or FetchType.LAZY then by default lazy loading will happend and data won't be laoded till we call above mentioned method on that.

To fetch everything at once we will have to set fetch = FetchType.EAGER

**HIBERNATE/PERSISTENCE Lifecycle states**

We have database and _session Object_, where in database we save relational data in tabular form but in session Object or hibernate session Object acts as a mediator, we have some methods in it such as create, update, delete, save, etc using which we can manipulate data in database.

The Hibernate life cycle consists of four main states: Transient, Persistent, Detached, and Removed. Each of these states represents a specific state of an object in the Hibernate framework.

_1) Transient_: When we create an object and object's variables has been initialized. (Object is neither in database nor in session).

_2) Persistent_: When we call save method from session this data will be associated with session and session will have referrence to this object and session will put the data in persistent state and data will be saved in database table. So if we change the value of an object which is in persistent state it's value will change in db table as well. It is synchronized in both session referrence and db table. If we close the session, object referrence will not be part of session. Object is synchronized with database and session Object while object is in persistent state.

_3) Detached_: After closing session or clearing session, object will not be associated with session and onject referrence from the session Object but object data will be present in database and this state is known as detached state. If we call update or any session Object method on data object, it will again go in persistent state.

_4) Removed_: If our data object is still part of session Object and it is in persistent state and we call delete on it then the value will be deleted from database table and the object will be in removed state as it will only be part of session Object now.

A Session is used to get a physical connection with a database. The Session object is lightweight and designed to be instantiated each time an interaction is needed with the database. Persistent objects are saved and retrieved through a Session object.

The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed. The main function of the Session is to offer, create, read, and delete operations for instances of mapped entity classes.

**Hibernate Lifecycle | States in Hibernate: Transient, Persistent, Detached, Removed**

![image](https://github.com/user-attachments/assets/d771cf92-79a6-4fe4-be22-a0d545d18db9)


Transient State
When an object is created using the “new” keyword, it is in the transient state. The object is not associated with any Hibernate session, and no database operations are performed on it. The object is simply a plain Java object (POJO) that is not yet persisted in the database.

Code Example:

`// Creating a new object in the transient state
Employee employee = new Employee();
employee.setName("John");
employee.setAge(30);`

Persistent State
When an object is associated with a Hibernate session, it enters the persistent state. In this state, the object is associated with a specific Hibernate session and is actively managed by Hibernate. Any changes made to the object will be tracked by Hibernate and will be persisted to the database when the session is flushed.

There are two sub-states of the persistent state: Transient-Persistent and Persistent-Detached.

Transient-Persistent State
When an object is first associated with a Hibernate session, it is in the Transient-Persistent state. This means that the object is newly created, and its state is not yet synchronized with the database. Any changes made to the object in this state will be persisted to the database when the session is flushed.

Code Example:

`// Creating a new object in the Transient-Persistent state
Employee employee = new Employee();
employee.setName("John");
employee.setAge(30); 
// Associating the object with a Hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();
session.save(employee);`


Persistent-Detached State
On the other hand, when an object is already in the database and is loaded into a Hibernate session, it is in the Persistent-Detached state. Any changes made to the object in this state will also be tracked by Hibernate and will be persisted to the database when the session is flushed.

Code Example:

`// Loading an existing object into a Hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L); 
// Modifying the object in the Persistent-Detached state
employee.setName("Alice"); 
// Persisting the changes to the database
session.beginTransaction();
session.update(employee);`

Detached State
When a persistent object is no longer associated with a Hibernate session, it enters the detached state. This means that the object is no longer actively managed by Hibernate, and any changes made to it will not be persisted to the database. However, the object is still a valid Java object and can be re-associated with a Hibernate session in the future.

Code Example:

`// Loading an existing object into a Hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L); 
// Detaching the object from the Hibernate session
session.evict(employee); 
// Modifying the object in the Detached state
employee.setAge(35); 
// Re-associating the object with a Hibernate session
session.beginTransaction();
session.update(employee);`

Removed State
When an object is deleted from the database, it enters the removed state. This means that the object is no longer associated with the database, and any attempts to modify it or re-associate it with a Hibernate session will result in an exception.

Code Example:

`// Loading an existing object into a Hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L); 
// Deleting the object from the database and entering the Removed state
session.beginTransaction();
session.delete(employee);`

Hibernate Life Cycle Transitions
Now that we have covered all the Hibernate life cycle states, let’s take a look at the transitions that occur between them.

Transient -> Persistent
The transient object becomes persistent when it is associated with a Hibernate session using the “save()” or “persist()” methods.

Code Example:

`// Creating a new object in the transient state
Employee employee = new Employee();
employee.setName("John");
employee.setAge(30);
// Associating the object with a Hibernate session and entering the Persistent state
Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();
session.save(employee);`

Persistent -> Detached
A persistent object becomes detached when the Hibernate session is closed, or the object is explicitly evicted from the session.

Code Example:

`// Loading an existing object into a Hibernate session and entering the Persistent state
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L);
// Evicting the object from the session and entering the Detached state
session.evict(employee);`

Detached -> Persistent
A detached object becomes persistent when it is re-associated with a Hibernate session using the “update()” or “merge()” methods.

Code Example:

`// Loading an existing object into a Hibernate session and entering the Persistent state
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L);
// Evicting the object from the session and entering the Detached state
session.evict(employee);
// Modifying the object in the Detached state
employee.setAge(35);
// Re-associating the object with a Hibernate session and entering the Persistent state
session.beginTransaction();
session.update(employee);`

Persistent -> Removed
A persistent object becomes removed when it is deleted from the database using the “delete()” method.

Code Example:

`// Loading an existing object into a Hibernate session and entering the Persistent state
Session session = HibernateUtil.getSessionFactory().openSession();
Employee employee = session.get(Employee.class, 1L);
// Deleting the object from the database and entering the Removed state
session.beginTransaction();
session.delete(employee);`
    
**Refference**
  <Br>
  <a href="https://www.baeldung.com/jpa-basic-annotation#conclusion" target="_blank">Ref-Basic-Annotation</a><Br>
  <a href="https://www.baeldung.com/hibernate-lazy-eager-loading" target="_blank">Ref-Lazy-Eager-Loading</a>
</p>
