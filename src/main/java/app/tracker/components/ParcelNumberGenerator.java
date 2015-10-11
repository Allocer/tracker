package app.tracker.components;

import app.tracker.enums.ParcelType;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class ParcelNumberGenerator
{
    private static final int NUMBER_LENGTH = 24;
    private static final boolean WITH_LETTERS = false;
    private static final boolean WITH_NUMBERS = true;

    public String generateNumber( final ParcelType type )
    {
        String number;
        switch ( type )
        {
            case LETTER:
                number = "L";
                break;
            case PACKAGE:
                number = "P";
                break;
            default:
                number = "U";
                break;
        }
        number += RandomStringUtils.random( NUMBER_LENGTH, WITH_LETTERS, WITH_NUMBERS );

        return number;
    }
}
