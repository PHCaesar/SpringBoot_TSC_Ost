package at.tsc.sj23_tsc_ost.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {


    /*
    Makes almost no sense (Embedded Entity shouldn't be treated equal to a normal Entity)

    @Version
    private Integer Version;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

     */
    @NotEmpty
    @NotBlank
    private String zipCode;

    @NotEmpty
    @NotBlank
    private String countryCode;

    @NotEmpty
    @NotBlank
    private String streetName;
}
