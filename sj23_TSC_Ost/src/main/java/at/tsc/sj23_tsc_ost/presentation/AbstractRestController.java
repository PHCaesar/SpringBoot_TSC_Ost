package at.tsc.sj23_tsc_ost.presentation;

import at.tsc.sj23_tsc_ost.presentation.exceptions.BadArgumentException;
import at.tsc.sj23_tsc_ost.presentation.exceptions.InternalServerError;
import com.sun.jdi.InternalException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public abstract class AbstractRestController {

    public <T> T wrappedServiceException(Supplier<T> supplier){
        if(supplier == null){
            String nullSupplierError = "Null supplier passed into wrappedServiceException";
            log.warn(nullSupplierError);
            throw new InternalServerError(nullSupplierError,null);
        }
        try {
            return supplier.get();
        } catch (IllegalArgumentException | NullPointerException exception){
            log.warn("Encountered problem while updating entity - Type : {} - Msg : {}",exception.getClass().getSimpleName(),exception.getMessage());
            throw new BadArgumentException(exception.getMessage(),exception);
        }
    }

}
