package app.tracker.dtos;

public class ParcelDetailDto
{
    private Long id;
    private int length;
    private int height;
    private int width;
    private float weight;

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

    public float getWeight()
    {
        return weight;
    }

    public void setWeight( float weight )
    {
        this.weight = weight;
    }
}
