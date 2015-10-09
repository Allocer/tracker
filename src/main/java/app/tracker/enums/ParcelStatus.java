package app.tracker.enums;


import java.io.Serializable;

public enum ParcelStatus implements Serializable
{
    SEND,
    CANCELED,
    RETURNED,
    ACCEPTED,
    DELIVERY,
    SORTING_PLANT
}
