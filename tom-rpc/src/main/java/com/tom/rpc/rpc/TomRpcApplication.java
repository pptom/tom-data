package com.tom.rpc.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author tom.tang
 * @date 2018/4/11
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/11
 */
@SpringBootApplication
public class TomRpcApplication {

    private final static Logger logger = LoggerFactory.getLogger(TomRpcApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(TomRpcApplication.class, args);
        while (true) {
            char c;
            try {
                c = (char) System.in.read();
                if (c == '\n') {
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
//        com.alibaba.dubbo.container.Main.main(args);
    }
}
