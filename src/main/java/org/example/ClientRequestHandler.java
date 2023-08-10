package org.example;


import org.example.calculator.domain.calculator;
import org.example.calculator.domain.PositiveNumber;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class ClientRequestHandler implements Runnable{

    private static final Logger logger=LoggerFactory.getLogger(ClientRequestHandler.class);

    @Override
    public  void run() {
        /**
         * step2-사용자 요청이 들어올때마다 thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
         *
         */
        logger.info("[ClientRequestHandler] new client,{} started",Thread.currentThread().getName());
        try(InputStream in=clientSocket.getInputStream(); OutputStream out=clientSocket.getOutputStream()){
        BufferReader br=new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8));
        DataOutputStream dos=new DataOutputStream(out);



        try(InputRequest.isGetRequest()&&httpRequest.matchPath({
                QueryStrings queryStrings=httpRequest.getQueryStrings();

                int operand1=Integer.parseInt(queryStrings.getValue("operand1"));
                String operator =queryStrings.getValue("operator");
                int operand2=Integer.parseInt(queryStrings.getValue("operand2"));

                int result=Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));
                byte[] body= String.valueOf(result).getBytes();

                HttpResponse response=new HttpResponse(dos);
                response.response200Header("application/json",body.length);
                response.responseBody(body);
        }
    }
    }
}