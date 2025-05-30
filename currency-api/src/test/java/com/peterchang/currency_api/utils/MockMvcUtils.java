package com.peterchang.currency_api.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.peterchang.currency_api.common.utils.DateUtils;

public class MockMvcUtils {
	
	/**
     * 將 MockMvc 測試輸出與印出上下行資訊
     * 
     * @param ResultActions actions
     */
    public static void printWithFormattedJson(ResultActions actions) throws Exception {
    	
    	Date now = DateUtils.now();

		File dir = new File("src/test/resources/test/" + DateUtils.getSimpleISODateStr(now));
		
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File outFile = new File(dir.getPath() + "/TestResult_" + DateUtils.getSimpleISODateTimeStr(now) + ".txt");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(outFile, false), true)) {
        	
        	ObjectMapper mapper = new ObjectMapper();
            ObjectWriter prettyWriter = mapper.writerWithDefaultPrettyPrinter();
        	
        	MvcResult result = actions.andDo(MockMvcResultHandlers.print(writer)).andReturn();
        	
        	writer.println("\n[RequestBody]");
            writer.println("========================================");
            
            String reqBody = result.getRequest().getContentAsString();
            if (reqBody != null && !reqBody.trim().equals("")) {
                try {
                	
                    Object json = mapper.readValue(reqBody, Object.class);
                    writer.println(prettyWriter.writeValueAsString(json));
                } catch (Exception e) {
                	
                    writer.println("(無法解析為 JSON)");
                    writer.println(reqBody);
                }
            } else {
                writer.println("(無 Request Body)");
            }
            
            writer.println("========================================");
            writer.println("\n[ResponseBody]");
            writer.println("========================================");
            
            String resBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
            try {
            	
                Object json = mapper.readValue(resBody, Object.class);
                writer.println(prettyWriter.writeValueAsString(json));
            } catch (Exception e) {
            	
                writer.println("(無法解析為 JSON)");
                writer.println(resBody);
            }          

            writer.println("========================================");
        }

        System.out.println("測試結果輸出完成：" + outFile.getAbsolutePath());
    }
}
