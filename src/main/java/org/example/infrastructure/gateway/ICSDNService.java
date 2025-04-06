package org.example.infrastructure.gateway;

import org.example.infrastructure.gateway.dto.ArticleRequestDTO;
import org.example.infrastructure.gateway.dto.ArticleResponseDTO;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * CSDN服务接口
 * 使用retrofit2框架封装对CSDN API的调用
 */
public interface ICSDNService {

    /**
     * 保存文章
     *
     * @param cookie Cookie信息
     * @param request 保存文章请求
     * @return 保存文章响应
     */
    @POST("https://bizapi.csdn.net/blog-console-api/v1/postedit/saveArticle")
    @Headers({
            "accept: application/json, text/plain, */*",
            "accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
            "content-type: application/json;",
            "dnt: 1",
            "priority: u=1, i",
            "sec-ch-ua: \"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Microsoft Edge\";v=\"134\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"macOS\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "x-ca-key: 203803574",
            "x-ca-nonce: 3c69d94c-b84b-4abd-9161-7da1a4aa2483",
            "x-ca-signature: x5/V9kX7svRmoT44T3JtfmEgW9A2Q/yD4oa4YiVQZgY=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce",
            "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/134.0.0.0",
            "origin: https://mpbeta.csdn.net",
            "referer: https://mpbeta.csdn.net/"
    })
    Call<ArticleResponseDTO> saveArticle(
            @Header("Cookie") String cookie,
            @Body ArticleRequestDTO request
    );
}