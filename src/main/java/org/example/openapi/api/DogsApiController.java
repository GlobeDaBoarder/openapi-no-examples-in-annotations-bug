package org.example.openapi.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Controller
@RequestMapping("${openapi.noExamplesInAnnotationExample.base-path:}")
public class DogsApiController implements DogsApi {

    private final DogsApiDelegate delegate;

    public DogsApiController(@Autowired(required = false) DogsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new DogsApiDelegate() {});
    }

    @Override
    public DogsApiDelegate getDelegate() {
        return delegate;
    }

}
