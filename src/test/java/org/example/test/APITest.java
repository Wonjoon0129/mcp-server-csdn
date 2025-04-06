package org.example.test;

import com.alibaba.fastjson.JSON;
import org.example.domain.model.ArticleFunctionRequest;
import org.example.domain.model.ArticleFunctionResponse;
import org.example.domain.service.CSDNArticleService;
import org.example.infrastructure.gateway.ICSDNService;
import org.example.infrastructure.gateway.dto.ArticleRequestDTO;
import org.example.infrastructure.gateway.dto.ArticleResponseDTO;
import org.example.type.properties.CSDNApiProperties;
import org.example.type.utils.MarkdownConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CSDN API测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class APITest {

    private final Logger log = LoggerFactory.getLogger(APITest.class);

    @Autowired
    private ICSDNService csdnService;


    @Autowired
    private CSDNArticleService csdnArticleService;
    @Autowired
    CSDNApiProperties csdnApiProperties;

    /**
     * 测试保存文章接口
     */
    @Test
    public void testSaveArticle() throws IOException {
        // 构建请求对象
        ArticleRequestDTO request = ArticleRequestDTO.builder()
                .article_id("")
                .title("测试文章标题")
                .description("测试文章描述")
                .content("<p>测试文章内容</p>\n")
                .tags("测试")
                .categories("")
                .type("original")
                .status(0)
                .read_type("public")
                .reason("")
                .original_link("")
                .authorized_status(false)
                .check_original(false)
                .source("pc_postedit")
                .not_auto_saved(1)
                .creator_activity_id("")
                .cover_images(new ArrayList<>())
                .cover_type(1)
                .vote_id(0)
                .resource_id("")
                .scheduled_time(0)
                .is_new(1)
                .sync_git_code(0)
                .build();

        // 示例Cookie，实际使用时需要传入有效的Cookie
        //String cookie = "uuid_tt_dd=10_6139708040-1730083611310-572882; UserName=kjihi; UserToken=5a3e781eb00c487eb61e2c9e8ee89d85";
        String cookie=csdnApiProperties.getCookie();
        // 调用接口
        Call<ArticleResponseDTO> call = csdnService.saveArticle(
                cookie,
                request
        );

        // 执行请求
        Response<ArticleResponseDTO> response = call.execute();

        // 打印响应结果
        if (response.isSuccessful() && response.body() != null) {
            ArticleResponseDTO result = response.body();
            System.out.println("保存文章成功：" + result);
        } else {
            System.out.println("保存文章失败：" + response.errorBody().string());
        }
    }

    @Test
    public void test_md2html() {
        System.out.println(MarkdownConverter.convertToHtml("**关于DDD是什么，在维基百科有一个明确的定义。\"Domain-driven design (DDD) is a major software design approach.\" 也就是说DDD是一种主要的软件设计方法。而软件设计涵盖了；范式、模型、框架、方法论。**\n" +
                "\n" +
                "- 范式（paradigm）指的是一种编程思想。\n" +
                "- 模型（model）指的是对现实世界或者问题的抽象描述。\n" +
                "- 框架（framework）指的是提供了一系列通用功能和结构的软件工具。\n" +
                "- 方法论（methodology）指的是一种系统的、有组织的解决问题的方法。\n" +
                "\n" +
                "所以，DDD不只是只有指导思想，伴随的DDD的还包括框架结构分层。但说到底，这些仍然是理论讨论。在没有一个DDD落地项目物参考下，其实大部分码农是没法完成DDD开发的。所以小傅哥今年花费了5个月假期/周末的时间，完成的《DDD简明开发教程》，帮助大家落地DDD编码。"));
    }

    @Test
    public void test_domain_saveArticle() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = csdnArticleService.saveArticle(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }
}