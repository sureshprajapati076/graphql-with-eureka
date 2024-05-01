package com.server.a.service;

import com.server.a.dto.ResponseB;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyService {

    @Autowired
    private HelperService helperService;

    @Retry(name="serviceb")
    public ResponseB callService(Map<String,String> reqMap){
        return helperService.helper(reqMap);
    }

    public void resetCount() {
        helperService.helperResetCounter();
    }
}
