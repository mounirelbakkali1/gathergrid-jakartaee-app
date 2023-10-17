package ma.youcode.gathergrid.utils;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Response<T> {
    private T result ;
    private List<Error> error ;

}
