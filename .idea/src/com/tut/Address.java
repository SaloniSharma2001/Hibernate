import javax.persistence.Table;

@Entity
@Table(name = "student_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Column(length = 50, name = "STREET")
    private String street;

    @Column(length = 100, name = "CITY")
    private String city;
    @Column(name = "IS_OPEN")
    private boolean isOpen;
    @Transient
    private double zipCode;
    @Column(name = "DATE_CREATED")
    //So this will give us date in ISO 8601 format (yyyy-MM-dd) and not as 'yyyy-MM-dd HH:mm:ss'
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Lob
    @Column(name = "IMAGE")
    //This will store the image as a byte array
    private byte[] image;


    public Address() {
        super();
    }

    public Address(int addressId, String street, String city, boolean isOpen, double zipCode, Date dateCreated, byte[] image) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.isOpen = isOpen;
        this.zipCode = zipCode;
        this.dateCreated = dateCreated;
        this.image = image;
    }

}