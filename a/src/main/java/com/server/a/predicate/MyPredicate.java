package com.server.a.predicate;

import com.server.a.dto.ResponseB;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class MyPredicate implements Predicate<ResponseB> {
    @Override
    public boolean test(ResponseB s) {
        boolean status = s.count()!=101;
        if(status){
            System.out.println("Performing Reattempt");
        }
        return status;
    }
}
