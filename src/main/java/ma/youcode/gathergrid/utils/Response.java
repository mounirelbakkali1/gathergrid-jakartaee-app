package ma.youcode.gathergrid.utils;


import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private T result ;
    private List<Error> error ;


}
