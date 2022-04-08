package org.mrdaios;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Journey1 {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:C:\\Users\\Administrator\\Desktop").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                }).to("file:C:\\Users\\Administrator\\Desktop1");
            }
        });
        while (true) {
        }
    }
}
