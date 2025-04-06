package org.example;

import jakarta.annotation.Resource;
import okhttp3.OkHttpClient;
import org.example.domain.service.CSDNArticleService;
import org.example.infrastructure.gateway.ICSDNService;
import org.example.type.properties.CSDNApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class McpServerApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(McpServerApplication.class);

    @Resource
    private CSDNApiProperties csdnApiProperties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    /**
     * 配置CSDN服务接口Bean
     */
    @Bean
    public ICSDNService csdnService() {
        // 配置OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        // 配置Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bizapi.csdn.net/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // 创建CSDN服务接口实例
        return retrofit.create(ICSDNService.class);
    }

    @Bean
    public ToolCallbackProvider csdnTools(CSDNArticleService csdnArticleService) {
        return MethodToolCallbackProvider.builder().toolObjects(csdnArticleService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("check csdn cookie ...");
        if (csdnApiProperties.getCookie() == null) {
            log.warn("csdn cookie key is null, please set it in application.yml");
        } else {
            log.info("csdn cookie  key is {}", csdnApiProperties.getCookie());
        }
    }
}
