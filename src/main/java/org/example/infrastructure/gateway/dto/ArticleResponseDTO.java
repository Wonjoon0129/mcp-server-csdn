package org.example.infrastructure.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSDN文章保存响应DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {
    private Integer code;
    private String traceId;
    private ArticleData data;
    private String msg;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleData {
        private String url;
        private Long article_id;
        private String qrcode;
        private String title;
        private String description;
    }
}