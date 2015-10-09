package app.tracker.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ParcelDetail implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private int length;

    @Column
    private int height;

    @Column
    private int width;

    @Column
    private float weight;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Parcel parcel;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength( int length )
    {
        this.length = length;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth( int width )
    {
        this.width = width;
    }

    public Parcel getParcel()
    {
        return parcel;
    }

    public void setParcel( Parcel parcel )
    {
        this.parcel = parcel;
    }

    public float getWeight()
    {
        return weight;
    }

    public void setWeight( float weight )
    {
        this.weight = weight;
    }
}
