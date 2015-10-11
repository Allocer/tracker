package app.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration( exclude = { VelocityAutoConfiguration.class } )
public class TrackerApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run( TrackerApplication.class, args );
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter()
    {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding( "UTF-8" );
        characterEncodingFilter.setForceEncoding( true );
        return characterEncodingFilter;
    }
}
